package song.sam.cozytrain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import song.sam.cozytrain.data.database.dao.AppDAO
import song.sam.cozytrain.data.remote.RemoteAPI
import song.sam.cozytrain.domain.repository.LastUploadRepository
import song.sam.cozytrain.domain.repository.LastUploadRepositoryImpl
import song.sam.cozytrain.domain.repository.remote.RemoteRepository
import song.sam.cozytrain.domain.repository.remote.RemoteRepositoryImpl
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideLastUploadRepository(
        dao: AppDAO,
        @Named("logTag") logTag: String
    ): LastUploadRepository =
        LastUploadRepositoryImpl(dao, logTag)

    @Provides
    fun provideRemoteRepository(
        @Named("logTag") logTag: String,
        remoteAPI: RemoteAPI
    ): RemoteRepository =
        RemoteRepositoryImpl(
            logTag = logTag,
            remoteAPI = remoteAPI
        )
}