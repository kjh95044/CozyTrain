package song.sam.cozytrain.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_upload")
data class LastUpload(
    @PrimaryKey
    val type: Type,
    var timestamp: Long //in seconds
) {
    enum class Type {
        HeartRate,
        Sleep,
        Steps,
    }
}

