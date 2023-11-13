package song.sam.cozytrain.domain.processing

import java.time.ZonedDateTime

data class PureChartRecord(
    val day: ZonedDateTime,
    val score: Float
)

data class NullableChartRecord(
    val day: ZonedDateTime,
    val score: Float?,
)

data class HybridChartRecord(
    val day: ZonedDateTime,
    val score: Float,
    val simulated: Boolean = false
)
