package song.sam.cozytrain.data.settings

import kotlinx.coroutines.flow.Flow

/**
 * Contains the operations related with the settings of the app
 * @see AppSettingsImpl
 */
interface AppSettings {

    /**
     * Save dynamic color palette preference
     * @param value: Preference to save
     */
    suspend fun saveDynamicColors(value: Boolean)

    /**
     * Get dynamic colors theme preference
     * @return [Flow] of [Boolean] with the values
     */
    suspend fun getDynamicColors(): Flow<Boolean>
}