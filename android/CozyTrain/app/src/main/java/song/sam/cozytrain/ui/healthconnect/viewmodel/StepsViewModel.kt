package song.sam.cozytrain.ui.healthconnect.viewmodel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.health.connect.client.records.StepsRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import song.sam.cozytrain.data.healthconnect.sources.Steps
import song.sam.cozytrain.ui.component.ViewModelData
import javax.inject.Inject

@HiltViewModel
class StepsViewModel @Inject constructor(
    private val steps: Steps
) : HealthConnectViewModel<StepsRecord>() {

    @Composable
    override fun getViewModelData(): ViewModelData<StepsRecord> {
        val data by elements
        val onPermissionsResult = { readData(steps) }

        val permissionsLauncher =
            rememberLauncherForActivityResult(permissionLauncher) { onPermissionsResult() }

        return ViewModelData(
            data = data,
            uiState = uiState,
            permissions = steps.readPermissions,
            onPermissionsResult = onPermissionsResult,
            onRequestPermissions = { values -> permissionsLauncher.launch(values) },
        )
    }
}