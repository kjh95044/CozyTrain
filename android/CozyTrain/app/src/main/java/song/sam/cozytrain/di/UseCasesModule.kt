package song.sam.cozytrain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import song.sam.cozytrain.data.healthconnect.sources.HeartRate
import song.sam.cozytrain.data.healthconnect.sources.Sleep
import song.sam.cozytrain.data.healthconnect.sources.Steps
import song.sam.cozytrain.data.info.AppInfo
import song.sam.cozytrain.domain.repository.LastUploadRepository
import song.sam.cozytrain.domain.repository.remote.RemoteRepository
import song.sam.cozytrain.domain.usecases.PostUserDataUseCase
import song.sam.cozytrain.domain.usecases.PostUserDataUseCaseImpl
import javax.inject.Named

/**
 * Use cases are used in multiple parts on the app and one of these uses is by Worker objects.
 * Worker objects can use only SingletonComponent dependencies and their transitive dependencies
 * also need to be SingletonComponent
 */

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun providePostUserDataUseCase(
        @Named("logTag") logTag: String,
        appInfo: AppInfo,
        remoteRepository: RemoteRepository,
        lastUploadRepository: LastUploadRepository,
        heartRate: HeartRate,
        sleep: Sleep,
        steps: Steps,
    ): PostUserDataUseCase =
        PostUserDataUseCaseImpl(
            logTag = logTag,
            appInfo = appInfo,
            remoteRepository = remoteRepository,
            lastUploadRepository = lastUploadRepository,
            heartRate = heartRate,
            sleep = sleep,
            steps = steps,
        )

}