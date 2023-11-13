package song.sam.cozytrain.ui.healthconnect.viewmodel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.health.connect.client.records.HeartRateRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import song.sam.cozytrain.data.healthconnect.sources.HeartRate
import song.sam.cozytrain.ui.component.ViewModelData
import javax.inject.Inject

@HiltViewModel
class HeartRateViewModel @Inject constructor(
    private val heartRate: HeartRate
) : HealthConnectViewModel<HeartRateRecord>() {
    @Composable
    override fun getViewModelData(): ViewModelData<HeartRateRecord> {
        val data by elements
        val onPermissionsResult = { readData(heartRate) }

        val permissionsLauncher =
            rememberLauncherForActivityResult(permissionLauncher) { onPermissionsResult() }

        return ViewModelData(
            data = data,
            uiState = uiState,
            permissions = heartRate.readPermissions,
            onPermissionsResult = onPermissionsResult,
            onRequestPermissions = { values -> permissionsLauncher.launch(values) },
        )
    }
}
