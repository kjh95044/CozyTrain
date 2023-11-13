package song.sam.cozytrain.model

import com.google.gson.annotations.SerializedName

data class HealthDataResponse(
    @SerializedName("error")
    val error: Error,

    @SerializedName("response")
    val response: HealthDataResponseDetails,

    @SerializedName("success")
    val success: Boolean
)

data class Error(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int
)

data class HealthDataResponseDetails(
    @SerializedName("caffeine")
    val caffeine: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("moveDist")
    val moveDist: Int,

    @SerializedName("sleepDuration")
    val sleepDuration: Int,

    @SerializedName("sleepScore")
    val sleepScore: Int,

    @SerializedName("sleepStages")
    val sleepStages: List<SleepStage>,

    @SerializedName("steps")
    val steps: Int,

    @SerializedName("stressLevel")
    val stressLevel: Int
)

