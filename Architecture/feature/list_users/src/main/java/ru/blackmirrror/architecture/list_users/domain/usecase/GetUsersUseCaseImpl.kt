package ru.blackmirrror.architecture.list_users.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.domain.ListUserRepository
import ru.blackmirrror.architecture.list_users.domain.model.User

internal class GetUsersUseCaseImpl(
    private val repository: ListUserRepository
): GetUsersUseCase {
    override suspend fun getUsers(): Flow<ResultState<List<User>>> {
        return repository.getUsers()
    }
}