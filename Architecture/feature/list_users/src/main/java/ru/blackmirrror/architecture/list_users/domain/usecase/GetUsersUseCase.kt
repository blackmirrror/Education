package ru.blackmirrror.architecture.list_users.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.domain.model.User

interface GetUsersUseCase {
    suspend fun getUsers(): Flow<ResultState<List<User>>>
}