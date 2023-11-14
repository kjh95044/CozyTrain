package song.sam.cozytrain.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val accessToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        Log.d("ㅋㅋ 토큰은", "$accessToken 임니다 ㅋㅋ ")
        return chain.proceed(request)
    }

}