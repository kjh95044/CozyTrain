package song.sam.cozytrain.domain.usecases

import song.sam.cozytrain.domain.repository.remote.RemoteOperationResult


interface PostUserDataUseCase {
    suspend fun shouldExecute(): Boolean
    suspend fun execute(): RemoteOperationResult
}