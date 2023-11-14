package song.sam.cozytrain.ui.component

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun BackHandlerMinimizeApp(activity: Activity) {
    BackHandler(enabled = true, onBack = {
        activity.moveTaskToBack(true)
    })
}
