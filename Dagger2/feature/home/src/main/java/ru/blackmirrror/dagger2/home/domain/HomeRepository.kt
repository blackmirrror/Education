package ru.blackmirrror.dagger2.home.domain

import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getData(): Flow<String>
}