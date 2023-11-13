package song.sam.cozytrain.ui.screens

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import song.sam.cozytrain.ui.screens.destinations.MyDataScreenDestination

/**
 * Root screen of DestinationsNavigator
 */
@RootNavGraph(start = true)
@Destination
@Composable
fun RootScreen(navigator: DestinationsNavigator) {
    navigator.navigate(MyDataScreenDestination())
}