package song.sam.cozytrain.data.remote.userdata

data class UserDataResponse(
    val code: Int,
    val timestamps: Timestamps?
) {
    data class Timestamps(
        val heartRate: Long?,
        val sleep: Long?,
        val steps: Long?,
    )
}
