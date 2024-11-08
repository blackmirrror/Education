package ru.blackmirrror.architecture.chat.domain

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.domain.model.Message
import ru.blackmirrror.architecture.chat.domain.model.User

interface ChatRepository {
    suspend fun getUser(id: String): Flow<ResultState<User>>
    suspend fun getMessages(userId: String): Flow<ResultState<List<Message>>>
    suspend fun sendMessage(userId: String, message: String): Flow<ResultState<Message>>
}