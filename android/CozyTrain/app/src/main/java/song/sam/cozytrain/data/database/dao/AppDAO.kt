package song.sam.cozytrain.data.database.dao

import androidx.room.*
import song.sam.cozytrain.data.database.entity.LastUpload

/**
 * Dao with all the operations related to database
 */
@Dao
interface AppDAO {

    /**
     * Insert a [LastUpload] value in database
     * @return ID of inserted row
     */
    @Insert
    suspend fun insert(lastUpload: LastUpload): Long

    /**
     * Update a [LastUpload] in database
     */
    @Update
    suspend fun update(lastUpload: LastUpload)

    /**
     * Query [LastUpload] by type
     * @return [LastUpload] instance if has been founded
     */
    @Query("SELECT * FROM last_upload WHERE type = :type")
    suspend fun getLastUpload(type: LastUpload.Type): LastUpload?

}