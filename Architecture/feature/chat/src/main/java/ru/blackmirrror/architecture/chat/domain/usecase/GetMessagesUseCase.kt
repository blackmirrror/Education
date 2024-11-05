package ru.blackmirrror.architecture.chat.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.model.Message

interface GetMessagesUseCase {
    suspend fun getMessages(userId: String): Flow<ResultState<List<Message>>>
}