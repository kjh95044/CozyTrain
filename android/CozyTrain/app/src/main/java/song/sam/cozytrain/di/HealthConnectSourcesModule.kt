package song.sam.cozytrain.di

import androidx.health.connect.client.HealthConnectClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import song.sam.cozytrain.data.healthconnect.sources.Sleep
import song.sam.cozytrain.data.healthconnect.sources.Steps
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object HealthConnectSourcesModule {

    @Provides
    fun provideSleep(
        healthConnectClient: HealthConnectClient,
        @Named("logTag") logTag: String
    ): Sleep = Sleep(healthConnectClient, logTag)

    @Provides
    fun provideSteps(
        healthConnectClient: HealthConnectClient
    ): Steps = Steps(healthConnectClient)

}