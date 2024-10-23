package ru.blackmirrror.dagger2.home.data.repository

import ru.blackmirrror.dagger2.core.network.ApiFactory
import ru.blackmirrror.dagger2.home.domain.FirstRepository
import javax.inject.Inject

internal class FirstRepoImpl @Inject constructor() : FirstRepository {

    override suspend fun getFirstResult(): Any = ApiFactory.createFirstApi()
}