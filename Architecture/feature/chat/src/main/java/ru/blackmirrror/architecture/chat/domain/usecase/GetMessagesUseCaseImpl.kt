package ru.blackmirrror.architecture.chat.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.ChatRepository
import ru.blackmirrror.architecture.chat.domain.model.Message

internal class GetMessagesUseCaseImpl(
    private val repository: ChatRepository
): GetMessagesUseCase {
    override suspend fun getMessages(userId: String): Flow<ResultState<List<Message>>> {
        return repository.getMessages(userId)
    }
}