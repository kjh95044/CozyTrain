package song.sam.cozytrain.ui.healthconnect.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.HeartRateRecord
import song.sam.cozytrain.R
import song.sam.cozytrain.ui.component.BasicCard
import song.sam.cozytrain.ui.component.DrawPair
import song.sam.cozytrain.ui.component.SeriesDateTimeHeading
import song.sam.cozytrain.ui.theme.BienestarEmocionalTheme
import song.sam.cozytrain.utils.formatHoursMinutes
import song.sam.cozytrain.utils.generateInterval
import song.sam.cozytrain.utils.linspace
import java.time.Duration
import java.time.Instant
import kotlin.random.Random

/**
 * Displays [HeartRateRecord]
 * @param widthSize: [WindowWidthSizeClass] to modify the component according to the screen
 */
@Composable
fun HeartRateRecord.Display(widthSize: WindowWidthSizeClass) {
    val unit = stringResource(id = R.string.bpm)

    BasicCard {
        SeriesDateTimeHeading(
            start = startTime,
            startZoneOffset = startZoneOffset,
            end = endTime,
            endZoneOffset = endZoneOffset
        )

        if (samples.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.heart_rate),
                color = MaterialTheme.colorScheme.onSurface
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp))
            {
                samples.forEach {
                    val formattedInstant = Duration.between(startTime, it.time).formatHoursMinutes()
                    DrawPair(key = "$formattedInstant ", value = "${it.beatsPerMinute} $unit")
                }
            }
        }
    }
}

fun generateHeartRateDummyData(): HeartRateRecord {
    val (init, end) = generateInterval()

    val numberSamples = 5
    val samples = linspace(init.toInstant().epochSecond, end.toInstant().epochSecond, numberSamples)
        .map { instant ->
            HeartRateRecord.Sample(
                Instant.ofEpochSecond(instant),
                Random.nextLong(60, 190)
            )
        }
    return HeartRateRecord(
        startTime = init.toInstant(),
        startZoneOffset = init.offset,
        endTime = end.toInstant(),
        endZoneOffset = end.offset,
        samples = samples
    )
}

@Preview(group = "Light Theme")
@Composable
fun HeartRateRecordDisplayPreview() {
    val heartRateRecord = generateHeartRateDummyData()
    BienestarEmocionalTheme {
        heartRateRecord.Display(widthSize = WindowWidthSizeClass.Compact)
    }
}


@Preview(group = "Light Theme")
@Composable
fun HeartRateRecordDisplayLargeScreenPreview() {
    val heartRateRecord = generateHeartRateDummyData()
    BienestarEmocionalTheme {
        heartRateRecord.Display(widthSize = WindowWidthSizeClass.Medium)
    }
}

