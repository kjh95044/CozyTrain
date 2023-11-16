package song.sam.cozytrain.ui.screens.mydata

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.health.connect.client.records.StepsRecord
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.ui.component.DrawHealthConnectSubscreen
import song.sam.cozytrain.ui.component.ViewModelData
import song.sam.cozytrain.ui.healthconnect.viewmodel.SleepSessionViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.StepsViewModel
import song.sam.cozytrain.utils.UserDataViewModel

@Destination
@Composable
fun MyDataScreen(
    sleepSessionViewModel: SleepSessionViewModel = hiltViewModel(),
    stepsViewModel: StepsViewModel = hiltViewModel(),
    dataViewModel: UserDataViewModel = hiltViewModel()
) {
    Log.d("dataViewModel","$dataViewModel")
    DrawMyDataScreen(
        sleepVMD = sleepSessionViewModel.getViewModelData(),
        stepsVMD = stepsViewModel.getViewModelData(),
        dataViewModel = dataViewModel
    )
}

@Composable
private fun DrawMyDataScreen(
    sleepVMD: ViewModelData<SleepSessionData>,
    stepsVMD: ViewModelData<StepsRecord>,
    dataViewModel: UserDataViewModel
) {
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
            userModelData = dataViewModel,
        )
    }

}