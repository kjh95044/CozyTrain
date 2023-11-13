package song.sam.cozytrain.ui.healthconnect.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.health.connect.client.records.StepsRecord
import song.sam.cozytrain.R
import song.sam.cozytrain.ui.component.BasicCard
import song.sam.cozytrain.ui.component.DrawPair
import song.sam.cozytrain.ui.theme.BienestarEmocionalTheme
import song.sam.cozytrain.utils.generateInterval
import kotlin.random.Random

/**
 * Displays [StepsRecord]
 * @param widthSize: [WindowWidthSizeClass] to modify the component according to the screen
 */
@Composable
fun StepsRecord.Display(widthSize: WindowWidthSizeClass) {
    BasicCard {
        Text(text = startTime.toString().substring(0, 10),
            color = MaterialTheme.colorScheme.onSurface)

        DrawPair(key = stringResource(id = R.string.steps), value = count.toString())

    }
}

private fun generateDummyData(): StepsRecord {
    val (init, end) = generateInterval()
    val count = Random.nextLong(0, 20000)
    return StepsRecord(
        startTime = init.toInstant(),
        startZoneOffset = init.offset,
        endTime = end.toInstant(),
        endZoneOffset = end.offset,
        count = count
    )
}

@Preview(group = "Light Theme")
@Composable
fun StepsRecordDisplayPreview() {
    val stepsRecord = generateDummyData()
    BienestarEmocionalTheme {
        stepsRecord.Display(widthSize = WindowWidthSizeClass.Compact)
    }
}


@Preview(group = "Light Theme")
@Composable
fun StepsRecordDisplayLargeScreenPreview() {
    val stepsRecord = generateDummyData()
    BienestarEmocionalTheme {
        stepsRecord.Display(widthSize = WindowWidthSizeClass.Medium)
    }
}
