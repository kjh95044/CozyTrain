package song.sam.cozytrain.data.remote.userdata

import song.sam.cozytrain.data.remote.senders.HeartRateSender
import song.sam.cozytrain.data.remote.senders.SleepSender
import song.sam.cozytrain.data.remote.senders.StepsSender

data class UserDataRequest(
    val data: Data
) {
    data class Data(
        val heartRate: List<HeartRateSender>?,
        val sleep: List<SleepSender>?,
        val steps: List<StepsSender>?,
    )

}