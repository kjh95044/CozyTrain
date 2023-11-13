package song.sam.cozytrain.di

import android.app.NotificationManager
import android.content.Context
import androidx.health.connect.client.HealthConnectClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import song.sam.cozytrain.data.RemoteConstants
import song.sam.cozytrain.data.healthconnect.HealthConnectAvailability
import song.sam.cozytrain.data.info.AppInfo
import song.sam.cozytrain.data.info.AppInfoImpl
import song.sam.cozytrain.data.remote.RemoteAPI
import song.sam.cozytrain.data.settings.AppSettings
import song.sam.cozytrain.data.settings.AppSettingsImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Contains services injected by Hilt common to the entire application
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHealthConnectClient(@ApplicationContext context: Context): HealthConnectClient =
        HealthConnectClient.getOrCreate(context)

    @Provides
    @Singleton
    fun provideHealthConnectAvailability(@ApplicationContext context: Context): HealthConnectAvailability =
        HealthConnectAvailability.getAvailability(context)


    @Provides
    @Singleton
    fun provideAppSettings(@ApplicationContext context: Context): AppSettings =
        AppSettingsImpl(context)

    @Provides
    @Singleton
    fun provideAppInfo(@ApplicationContext context: Context): AppInfo =
        AppInfoImpl(context)

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @Singleton
    @Named("logTag")
    fun provideLogTag(): String = "BienestarEmocionalApp"


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideRemoteAPI(gson: Gson): RemoteAPI = Retrofit.Builder()
        .baseUrl(RemoteConstants.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RemoteAPI::class.java)
}