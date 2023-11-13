package song.sam.cozytrain.ui.healthconnect.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.SleepStageRecord
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_AWAKE
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_DEEP
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_LIGHT
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_OUT_OF_BED
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_REM
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_SLEEPING
import androidx.health.connect.client.records.SleepStageRecord.Companion.STAGE_TYPE_UNKNOWN
import song.sam.cozytrain.R
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.ui.component.BasicCard
import song.sam.cozytrain.ui.component.DrawPair
import song.sam.cozytrain.ui.component.SeriesDateTimeHeading
import song.sam.cozytrain.utils.formatDisplayTimeStartEnd
import song.sam.cozytrain.utils.formatHoursMinutes

/**
 * Displays [SleepSessionData]
 * @param widthSize: [WindowWidthSizeClass] to modify the component according to the screen
 */
@Composable
fun SleepSessionData.Display(widthSize: WindowWidthSizeClass) {
    BasicCard {
        SeriesDateTimeHeading(
            start = startTime,
            startZoneOffset = startZoneOffset,
            end = endTime,
            endZoneOffset = endZoneOffset
        )

        duration?.let {
            val formattedDuration = duration.formatHoursMinutes()
            DrawPair(key = stringResource(R.string.duration), value = formattedDuration)
        }

        Text(
            text = stringResource(R.string.sleep_stages),
            color = MaterialTheme.colorScheme.onSurface
        )
        stages.forEach { it.Display() }
    }
}

@Composable
fun SleepStageRecord.Display() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    )
    {
        val intervalLabel = formatDisplayTimeStartEnd(
            startTime, startZoneOffset, endTime, endZoneOffset
        )

        Text(
            modifier = Modifier.weight(1f),
            text = intervalLabel,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.weight(1f),
            text = decode(),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun SleepStageRecord.decode(): String =
    when (stage) {
        STAGE_TYPE_UNKNOWN -> stringResource(R.string.unknown)
        STAGE_TYPE_AWAKE -> stringResource(R.string.awake)
        STAGE_TYPE_SLEEPING -> stringResource(R.string.sleeping)
        STAGE_TYPE_OUT_OF_BED -> stringResource(R.string.out_of_bed)
        STAGE_TYPE_LIGHT -> stringResource(R.string.light)
        STAGE_TYPE_DEEP -> stringResource(R.string.deep)
        STAGE_TYPE_REM -> stringResource(R.string.rem)
        else -> stringResource(R.string.unknown)
    }


