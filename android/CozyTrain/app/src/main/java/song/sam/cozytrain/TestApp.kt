package song.sam.cozytrain

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import song.sam.cozytrain.ui.screens.NavGraphs
import song.sam.cozytrain.ui.theme.BienestarEmocionalTheme

@Composable
fun TestApp(
) {
    BienestarEmocionalTheme()
    {
        DestinationsNavHost(navGraph = NavGraphs.root)
    }
}
