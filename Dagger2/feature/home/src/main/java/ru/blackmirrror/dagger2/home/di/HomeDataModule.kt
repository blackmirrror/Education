package ru.blackmirrror.dagger2.home.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.core.di.CoreComponent
import ru.blackmirrror.dagger2.core.di.First
import ru.blackmirrror.dagger2.core.di.Second
import ru.blackmirrror.dagger2.home.data.api.ApiServiceFirst
import ru.blackmirrror.dagger2.home.data.api.ApiServiceSecond
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class HomeDataModule {

    @[Provides Home]
    fun provideApiServiceFirst(deps: HomeDependenciesProvider): ApiServiceFirst {
        return deps.getRetrofitFirst().create(ApiServiceFirst::class.java)
    }

    @[Provides Home]
    fun provideApiServiceSecond(deps: HomeDependenciesProvider): ApiServiceSecond {
        return deps.getRetrofitSecond().create(ApiServiceSecond::class.java)
    }
}