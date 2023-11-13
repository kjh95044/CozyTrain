package song.sam.cozytrain.model

import androidx.health.connect.client.records.SleepStageRecord
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class dreamData (
    @SerializedName("dreamContent")
    val dreamContent: String,

    @SerializedName("dreamType")
    val dreamType: Int
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
    val endTime: String,

    @SerializedName("stage")
    val stage: Int,

    @SerializedName("startTime")
    val startTime: String
)

fun convertSleepStageToSleepStages(sleepStageRecords: List<SleepStageRecord>): List<SleepStage> {
    return sleepStageRecords.map {
        SleepStage(
            endTime = convertInstantToLocalDateTime(it.endTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
            stage = it.stage,
            startTime = convertInstantToLocalDateTime(it.startTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        )
    }
}

fun convertInstantToLocalDateTime(instant: Instant): LocalDateTime {
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}