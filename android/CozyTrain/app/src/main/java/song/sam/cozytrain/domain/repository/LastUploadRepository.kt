package song.sam.cozytrain.domain.repository

import song.sam.cozytrain.data.database.entity.LastUpload

interface LastUploadRepository {
    suspend fun insert(lastUpload: LastUpload): Long
    suspend fun update(lastUpload: LastUpload)
    suspend fun get(type: LastUpload.Type): LastUpload?
}