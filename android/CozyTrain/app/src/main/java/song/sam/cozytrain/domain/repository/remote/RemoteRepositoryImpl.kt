package song.sam.cozytrain.domain.repository.remote

import android.util.Log
import song.sam.cozytrain.data.remote.RemoteAPI
import song.sam.cozytrain.data.remote.userdata.UserDataRequest
import song.sam.cozytrain.data.remote.userdata.UserDataResponse

class RemoteRepositoryImpl(
    private val logTag: String,
    private val remoteAPI: RemoteAPI
) : RemoteRepository {

    override suspend fun postUserData(userDataRequest: UserDataRequest): UserDataResponse? {
        Log.d(logTag, "posting user data")

        var response: UserDataResponse? = null

        try {
            val rawResponse = remoteAPI.postUserData(userDataRequest)
            Log.d(
                logTag, "response received with " +
                        "code ${rawResponse.code()} and values ${rawResponse.body()}"
            )
            response = UserDataResponse(
                code = rawResponse.code(),
                timestamps = rawResponse.body()
            )
        }
        catch (e: Exception) {
            Log.e(logTag, "response failed with exception ${Log.getStackTraceString(e)}")
        }
        return response
    }

    override suspend fun postBackgroundData(message: String): Boolean {
        try {
            remoteAPI.postBackgroundData(message)

        }
        catch (e: Exception) {
            Log.e(logTag, "response failed with exception $e")
            return false
        }
        return true
    }
}
