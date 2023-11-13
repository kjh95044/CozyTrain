package song.sam.cozytrain.data.remote

import song.sam.cozytrain.data.remote.userdata.UserDataRequest
import song.sam.cozytrain.data.remote.userdata.UserDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Operations established on the API
 */
interface RemoteAPI {
    @POST("/user_data")
    suspend fun postUserData(@Body data: UserDataRequest): Response<UserDataResponse.Timestamps>

    @POST("/bg_data")
    suspend fun postBackgroundData(@Body message: String): Response<Unit>
}