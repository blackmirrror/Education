package ru.blackmirrror.dagger2.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.blackmirrror.dagger2.home.data.api.ApiServiceFirst
import ru.blackmirrror.dagger2.home.data.api.ApiServiceSecond
import ru.blackmirrror.dagger2.home.domain.HomeRepository
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val serviceFirst: ApiServiceFirst,
    private val serviceSecond: ApiServiceSecond
) : HomeRepository {

    override suspend fun getData(): Flow<String> = flow {
        val res = serviceFirst.getMovie(301)
        if (res.isSuccessful) {
            val res2 = serviceSecond.getMovie(302)
            if (res2.isSuccessful)
                emit("${res.body()?.nameRu} ${res2.body()?.nameRu}")
            else
                emit("error")
        } else
            emit("error")
    }
}
