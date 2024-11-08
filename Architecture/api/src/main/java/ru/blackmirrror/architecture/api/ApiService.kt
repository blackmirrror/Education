package ru.blackmirrror.architecture.api

import retrofit2.Response
import ru.blackmirrror.architecture.api.model.ChatDTO
import ru.blackmirrror.architecture.api.model.MessageDTO
import ru.blackmirrror.architecture.api.model.UserDTO

interface ApiService {
    suspend fun getUsers(): Response<List<UserDTO>>
    suspend fun getUser(): Response<UserDTO>
    suspend fun getMessages(chat: ChatDTO): Response<List<MessageDTO>>
    suspend fun sendMessage(messageDTO: MessageDTO): Response<MessageDTO>
    suspend fun getChat(userId1: String, userId2: String): Response<ChatDTO>

    // must be in shared prefs
    suspend fun getCurrentUser(): Response<UserDTO>
}