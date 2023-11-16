package song.sam.cozytrain.ui.healthconnect.viewmodel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import song.sam.cozytrain.data.healthconnect.sources.Sleep
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.ui.component.ViewModelData
import javax.inject.Inject

@HiltViewModel
class SleepSessionViewModel @Inject constructor(
    private val sleep: Sleep
) : HealthConnectViewModel<SleepSessionData>() {
    @Composable
    override fun getViewModelData(): ViewModelData<SleepSessionData> {
        val data by elements
        val onPermissionsResult = { readData(sleep) }
//        val hasPermission = init(sleep)

        val permissionsLauncher =
            rememberLauncherForActivityResult(permissionLauncher) { onPermissionsResult() }

        return ViewModelData(
            data = data,
            uiState = uiState,
            permissions = sleep.readPermissions,
            onPermissionsResult = onPermissionsResult,
            onRequestPermissions = { values -> permissionsLauncher.launch(values) },
//            hasPermission = hasPermission
        )
    }
}

