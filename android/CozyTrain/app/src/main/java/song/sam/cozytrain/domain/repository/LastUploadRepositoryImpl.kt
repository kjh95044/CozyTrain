package song.sam.cozytrain.domain.repository

import android.util.Log
import song.sam.cozytrain.data.database.dao.AppDAO
import song.sam.cozytrain.data.database.entity.LastUpload

class LastUploadRepositoryImpl(
    private val dao: AppDAO,
    private val logTag: String
) : LastUploadRepository {
    override suspend fun insert(lastUpload: LastUpload): Long {
        Log.d(logTag, "inserting new lastUpload, type ${lastUpload.type}")
        return dao.insert(lastUpload)
    }

    override suspend fun update(lastUpload: LastUpload) {
        Log.d(logTag, "updating lastUpload, type ${lastUpload.type}")
        return dao.update(lastUpload)
    }

    override suspend fun get(type: LastUpload.Type): LastUpload? {
        Log.d(logTag, "querying lastUpload of type $type")
        return dao.getLastUpload(type)
    }
}