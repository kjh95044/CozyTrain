package song.sam.cozytrain.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "data"
    )
    private val permissionKey = booleanPreferencesKey("permission")

    // 데이터를 저장하는 함수
    suspend fun savePermission(permission: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[permissionKey] = permission
        }
    }

    // 데이터를 가져오는 함수
    val permissionFlow: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[permissionKey]
        }

}
