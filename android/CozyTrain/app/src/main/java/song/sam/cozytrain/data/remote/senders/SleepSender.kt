package song.sam.cozytrain.data.remote.senders

import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.data.remote.senders.SleepStageSender.Companion.toSender
import song.sam.cozytrain.utils.obtainTimestamp

/**
 * Object used to send [SleepSessionData], usually through JSON files.
 */
data class SleepSender(
    val startTime: Long,
    val endTime: Long,
    val stages: List<SleepStageSender>
) {
    companion object {
        fun SleepSessionData.toSender(): SleepSender {
            val startTime = obtainTimestamp(startTime, startZoneOffset)
            val endTime = obtainTimestamp(endTime, endZoneOffset)
            val stages = stages.map { it.toSender() }
            return SleepSender(
                startTime = startTime,
                endTime = endTime,
                stages = stages
            )
        }
    }

}