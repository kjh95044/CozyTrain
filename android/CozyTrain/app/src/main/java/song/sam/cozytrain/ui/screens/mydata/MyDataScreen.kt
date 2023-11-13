package song.sam.cozytrain.ui.screens.mydata

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.ui.component.DrawHealthConnectSubscreen
import song.sam.cozytrain.ui.component.ViewModelData
import song.sam.cozytrain.ui.healthconnect.component.Display
import song.sam.cozytrain.ui.healthconnect.viewmodel.HeartRateViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.SleepSessionViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.StepsViewModel
import song.sam.cozytrain.ui.responsive.computeWindowWidthSize
import kotlinx.coroutines.launch

@Destination
@Composable
fun MyDataScreen(
    navigator: DestinationsNavigator,
    viewModel: MyDataViewModel = hiltViewModel(),
    sleepSessionViewModel: SleepSessionViewModel = hiltViewModel(),
    heartRateViewModel: HeartRateViewModel = hiltViewModel(),
    stepsViewModel: StepsViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()

    DrawMyDataScreen(
        navigator = navigator,
        widthSize = computeWindowWidthSize(),
        snackbarHostState = snackbarHostState,
        state = state,
        sleepVMD = sleepSessionViewModel.getViewModelData(),
        heartRateVMD = heartRateViewModel.getViewModelData(),
        stepsVMD = stepsViewModel.getViewModelData(),
        onSelect = { index -> viewModel.onSelect(index) },
        onUnselect = { viewModel.onUnselect() },
        onError = { exception -> viewModel.onError(snackbarHostState, exception) })
}

@Composable
private fun DrawMyDataScreen(
    navigator: DestinationsNavigator,
    widthSize: WindowWidthSizeClass,
    snackbarHostState: SnackbarHostState,
    state: MyDataState,
    sleepVMD: ViewModelData<SleepSessionData>,
    heartRateVMD: ViewModelData<HeartRateRecord>,
    stepsVMD: ViewModelData<StepsRecord>,
    onSelect: (Int) -> Unit,
    onUnselect: () -> Unit,
    onError: suspend (Throwable?) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        DrawHealthConnectSubscreen(
            viewModelData1 = stepsVMD,
            viewModelData2 = sleepVMD,
            viewModelData3 = heartRateVMD,
            onDisplayData = {
                Log.d("??", "${stepsVMD.data}")

            },
            onError = { coroutineScope.launch { onError(it) } }
        )
    }

}