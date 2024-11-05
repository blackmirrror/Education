package ru.blackmirrror.architecture.api

import retrofit2.Response
import ru.blackmirrror.architecture.api.model.UserDTO

interface ApiService {
    suspend fun getUsers(): Response<List<UserDTO>>
}