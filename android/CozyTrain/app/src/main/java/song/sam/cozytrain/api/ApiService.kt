package song.sam.cozytrain.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import song.sam.cozytrain.model.HealthData
import song.sam.cozytrain.model.HealthDataResponse
import song.sam.cozytrain.model.dreamData

interface ApiService {
    @POST("report")
    fun postHealthData(@Body healthData: HealthData) : Call<HealthDataResponse>

    @POST("dream")
    fun postDreamData(@Body dreamData: dreamData) : Call<HealthDataResponse>

}