package ru.blackmirrror.dagger2.home.data.repository

import ru.blackmirrror.dagger2.core.network.ApiFactory
import ru.blackmirrror.dagger2.home.domain.SecondRepository
import javax.inject.Inject

internal class SecondRepoImpl @Inject constructor() : SecondRepository {

    override suspend fun getSecondResult(): Any = ApiFactory.createSecondApi()
}