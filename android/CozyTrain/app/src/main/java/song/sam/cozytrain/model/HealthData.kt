package song.sam.cozytrain.model

import androidx.health.connect.client.records.SleepStageRecord
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZoneId

data class dreamData (
    @SerializedName("dreamContent")
    val dreamContent: String,

    @SerializedName("dreamType")
    val dreamType: Int
)

data class HealthData(
    @SerializedName("health")
    val health: Health
)

data class Health(
    @SerializedName("sleepDuration")
    val sleepDuration: Int,

    @SerializedName("sleepStages")
    val sleepStages: List<SleepStage>,

    @SerializedName("steps")
    val steps: Int,

    @SerializedName("stressLevel")
    val stressLevel: Int
)

data class SleepStage(
    @SerializedName("endTime")
    val endTime: LocalDateTime,

    @SerializedName("stage")
    val stage: Int,

    @SerializedName("startTime")
    val startTime: LocalDateTime
)

fun convertSleepStageToSleepStages(sleepStageRecords: List<SleepStageRecord>) : List<SleepStage> {
    return sleepStageRecords.map {
        SleepStage(
            endTime = it.endTime.atZone(ZoneId.systemDefault()).toLocalDateTime(),
            stage = it.stage,
            startTime = it.startTime.atZone(ZoneId.systemDefault()).toLocalDateTime()
        )
    }
}