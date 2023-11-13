package song.sam.cozytrain.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [AppSettings] using DataStore
 */
class AppSettingsImpl(private val context: Context) : AppSettings {
    companion object {
        /**
         * DataStore object used for ACID operations
         */
        private val Context.appSettingsDataStore: DataStore<Preferences>
                by preferencesDataStore(name = "settings")

        //preferences keys of the settings
        private val MEASURES = stringSetPreferencesKey("measures")
        private val THEME = stringPreferencesKey("theme")
        private val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")

        //defaults values
        private val MEASURES_DEFAULT_VALUE = emptySet<String>()
        private const val DYNAMIC_COLORS_DEFAULT_VALUE = false
    }

    override suspend fun saveDynamicColors(value: Boolean) {
        context.appSettingsDataStore.edit { preferences ->
            preferences[DYNAMIC_COLORS] = value
        }
    }

    override suspend fun getDynamicColors(): Flow<Boolean> =
        context.appSettingsDataStore.data.map { preferences ->
            preferences[DYNAMIC_COLORS] ?: DYNAMIC_COLORS_DEFAULT_VALUE
        }
}