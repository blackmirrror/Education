package ru.blackmirrror.architecture.chat.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.ChatRepository
import ru.blackmirrror.architecture.chat.domain.model.Message

internal class SendMessageUseCaseImpl(
    private val repository: ChatRepository
): SendMessageUseCase {
    override suspend fun sendMessage(userId: String, message: String): Flow<ResultState<Message>> {
        return repository.sendMessage(userId, message)
    }
}