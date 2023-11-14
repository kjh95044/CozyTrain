package song.sam.cozytrain.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserDataViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _isPermission = MutableStateFlow(false)
    val isPermission: StateFlow<Boolean> = _isPermission

    init {
        viewModelScope.launch {
            dataStore.data.map { preferences ->
                preferences[booleanPreferencesKey("isPermission")] ?: false
            }.collect { savedValue ->
                _isPermission.value = savedValue
            }
        }
    }

    fun updatePermission() {
        _isPermission.value = true

        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[booleanPreferencesKey("isPermission")] = true
            }
        }

    }
}
