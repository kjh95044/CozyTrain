package song.sam.cozytrain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import song.sam.cozytrain.data.healthconnect.sources.Sleep
import song.sam.cozytrain.data.healthconnect.sources.Steps
import song.sam.cozytrain.domain.repository.LastUploadRepository
import song.sam.cozytrain.domain.repository.remote.RemoteRepository
import song.sam.cozytrain.domain.usecases.PostUserDataUseCase
import song.sam.cozytrain.domain.usecases.PostUserDataUseCaseImpl
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun providePostUserDataUseCase(
        @Named("logTag") logTag: String,
        remoteRepository: RemoteRepository,
        lastUploadRepository: LastUploadRepository,
        sleep: Sleep,
        steps: Steps,
    ): PostUserDataUseCase =
        PostUserDataUseCaseImpl(
            logTag = logTag,
            remoteRepository = remoteRepository,
            lastUploadRepository = lastUploadRepository,
            sleep = sleep,
            steps = steps,
        )

}