package ru.blackmirrror.architecture.list_users.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.blackmirrror.architecture.api.ApiService
import ru.blackmirrror.architecture.api.utils.EmptyData
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.api.utils.ServerError
import ru.blackmirrror.architecture.list_users.domain.ListUserRepository
import ru.blackmirrror.architecture.list_users.domain.model.User
import ru.blackmirrror.architecture.list_users.domain.toUser

internal class ListUserRepositoryImpl(
    private val service: ApiService,
) : ListUserRepository {
    override suspend fun getUsers(): Flow<ResultState<List<User>>> {
        return flow {
            emit(ResultState.Loading())
            val res = service.getUsers()
            if (res.isSuccessful) {
                val users = res.body()
                if (users != null) {
                    emit(ResultState.Success(users.map { it.toUser() }))
                } else {
                    emit(ResultState.Error(EmptyData))
                }
            } else
                emit(ResultState.Error(ServerError))
        }
    }

}
