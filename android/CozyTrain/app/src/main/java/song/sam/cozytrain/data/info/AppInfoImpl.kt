package song.sam.cozytrain.data.info

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [AppInfo] using DataStore
 */
class AppInfoImpl(private val context: Context) : AppInfo {
    companion object {
        /**
         * DataStore object used for ACID operations
         */
        private val Context.appInfoDataStore: DataStore<Preferences>
                by preferencesDataStore(name = "info")

        //preferences keys of the settings
        private val FIRST_TIME = booleanPreferencesKey("first_time")
        private val USER_ID = stringPreferencesKey("user_id")

        //defaults values
        private const val FIRST_TIME_DEFAULT_VALUE = true
    }

    override suspend fun saveFirstTime(value: Boolean) {
        context.appInfoDataStore.edit { preferences ->
            preferences[FIRST_TIME] = value
        }
    }

    override suspend fun getFirstTime(): Flow<Boolean> =
        context.appInfoDataStore.data.map { preferences ->
            preferences[FIRST_TIME] ?: FIRST_TIME_DEFAULT_VALUE
        }


}