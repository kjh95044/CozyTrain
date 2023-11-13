package song.sam.cozytrain.domain.usecases

import android.util.Log
import song.sam.cozytrain.data.database.entity.LastUpload
import song.sam.cozytrain.data.healthconnect.sources.HeartRate
import song.sam.cozytrain.data.healthconnect.sources.Sleep
import song.sam.cozytrain.data.healthconnect.sources.Steps
import song.sam.cozytrain.data.info.AppInfo
import song.sam.cozytrain.data.remote.senders.HeartRateSender
import song.sam.cozytrain.data.remote.senders.HeartRateSender.Companion.toSender
import song.sam.cozytrain.data.remote.senders.SleepSender
import song.sam.cozytrain.data.remote.senders.SleepSender.Companion.toSender
import song.sam.cozytrain.data.remote.senders.StepsSender
import song.sam.cozytrain.data.remote.senders.StepsSender.Companion.toSender
import song.sam.cozytrain.data.remote.userdata.UserDataRequest
import song.sam.cozytrain.data.remote.userdata.UserDataResponse
import song.sam.cozytrain.domain.repository.LastUploadRepository
import song.sam.cozytrain.domain.repository.remote.RemoteOperationResult
import song.sam.cozytrain.domain.repository.remote.RemoteRepository
import java.time.Instant
import java.time.ZonedDateTime

class PostUserDataUseCaseImpl(
    private val logTag: String,
    private val appInfo: AppInfo,
    private val remoteRepository: RemoteRepository,
    private val lastUploadRepository: LastUploadRepository,
    private val heartRate: HeartRate,
    private val sleep: Sleep,
    private val steps: Steps,
) : PostUserDataUseCase {
    override suspend fun shouldExecute(): Boolean {
        return  heartRate.readPermissionsCheck() ||
                sleep.readPermissionsCheck() ||
                steps.readPermissionsCheck()
    }

    private suspend fun prepareUserData(): UserDataRequest {
        var heartRateData: List<HeartRateSender>? = null
        var sleepData: List<SleepSender>? = null
        var stepsData: List<StepsSender>? = null

        Log.d(logTag, "prepareUserData")

        // Read and convert data if we have permissions
        val now = ZonedDateTime.now().toInstant()


        if (heartRate.readPermissionsCheck()) {
            val lastUpload = lastUploadRepository.get(LastUpload.Type.HeartRate)
            val from = lastUpload?.let { Instant.ofEpochSecond(it.timestamp + 1) }
            heartRateData = (
                    from?.let { heartRate.readSource(it, now) }
                        ?: heartRate.readSource()
                    )
                .map { it.toSender() }
        }

        if (sleep.readPermissionsCheck()) {
            val lastUpload = lastUploadRepository.get(LastUpload.Type.Sleep)
            val from = lastUpload?.let { Instant.ofEpochSecond(it.timestamp + 1) }
            sleepData = (from?.let { sleep.readSource(it, now) } ?: sleep.readSource())
                .map { it.toSender() }
        }

        if (steps.readPermissionsCheck()) {
            val lastUpload = lastUploadRepository.get(LastUpload.Type.Steps)
            val from = lastUpload?.let { Instant.ofEpochSecond(it.timestamp + 1) }
            stepsData = (from?.let { steps.readSource(it, now) } ?: steps.readSource())
                .map { it.toSender() }
        }

        val userData = UserDataRequest.Data(
            heartRate = heartRateData,
            sleep = sleepData,
            steps = stepsData,
        )

        return UserDataRequest(
            data = userData
        )
    }

    private suspend fun processSuccessfulRequest(response: UserDataResponse) {
        // If we have a valid response from the server, update timestamps on database
        response.timestamps?.let { timestamps ->
            Log.d(logTag, "updating timestamps")


            timestamps.heartRate?.let {
                lastUploadRepository.update(
                    LastUpload(
                        type = LastUpload.Type.HeartRate,
                        timestamp = it
                    )
                )
            }

            timestamps.sleep?.let {
                lastUploadRepository.update(
                    LastUpload(
                        type = LastUpload.Type.Sleep,
                        timestamp = it
                    )
                )
            }

            timestamps.steps?.let {
                lastUploadRepository.update(
                    LastUpload(
                        type = LastUpload.Type.Steps,
                        timestamp = it
                    )
                )
            }

        }
    }

    override suspend fun execute(): RemoteOperationResult {
        val data = prepareUserData()
        val response = remoteRepository.postUserData(data)

        // If response is null, the result is failure
        var result = RemoteOperationResult.Failure

        response?.let {
            result = when (response.code) {
                in 200..299 -> RemoteOperationResult.Success
                in 500..599 -> RemoteOperationResult.ServerFailure
                else -> RemoteOperationResult.Failure
            }
            if (result == RemoteOperationResult.Success)
                processSuccessfulRequest(response)
        }

        return result
    }
}