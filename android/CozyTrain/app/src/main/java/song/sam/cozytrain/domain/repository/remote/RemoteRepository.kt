package song.sam.cozytrain.domain.repository.remote

import song.sam.cozytrain.data.remote.userdata.UserDataRequest
import song.sam.cozytrain.data.remote.userdata.UserDataResponse

/**
 * Repository to interact with the Remote API
 */
interface RemoteRepository {
    suspend fun postUserData(userDataRequest: UserDataRequest): UserDataResponse?
    suspend fun postBackgroundData(message: String): Boolean
}