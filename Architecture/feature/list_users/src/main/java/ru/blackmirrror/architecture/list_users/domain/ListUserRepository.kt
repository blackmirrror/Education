package ru.blackmirrror.architecture.list_users.domain

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.domain.model.User

interface ListUserRepository {
    suspend fun getUsers(): Flow<ResultState<List<User>>>
}