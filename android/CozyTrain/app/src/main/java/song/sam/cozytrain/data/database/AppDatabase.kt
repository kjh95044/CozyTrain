package song.sam.cozytrain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import song.sam.cozytrain.data.database.dao.AppDAO
import song.sam.cozytrain.data.database.entity.LastUpload

/**
 * Instance of app database
 */
@Database(
    entities = [
        LastUpload::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDAO
}