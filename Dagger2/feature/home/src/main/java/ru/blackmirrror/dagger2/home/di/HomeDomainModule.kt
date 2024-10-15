package ru.blackmirrror.dagger2.home.di

import dagger.Module
import dagger.Provides
import ru.blackmirrror.dagger2.home.data.api.ApiServiceFirst
import ru.blackmirrror.dagger2.home.data.api.ApiServiceSecond
import ru.blackmirrror.dagger2.home.data.repository.HomeRepositoryImpl
import ru.blackmirrror.dagger2.home.domain.HomeRepository
import javax.inject.Singleton

@Module
class HomeDomainModule {
    @Provides
    fun provideHomeRepository(serviceFirst: ApiServiceFirst, serviceSecond: ApiServiceSecond): HomeRepository {
        return HomeRepositoryImpl(serviceFirst, serviceSecond)
    }
}