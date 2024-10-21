package ru.blackmirrror.dagger2.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.home.di.HomeDependenciesProvider

@Module
class HomeDependenciesProviderImpl(private val appComponent: AppComponent) :
    HomeDependenciesProvider {
        @Provides
    override fun getRetrofitFirst(): Retrofit {
        return appComponent.getRetrofitFirst()
    }

    override fun getRetrofitSecond(): Retrofit {
        return appComponent.getRetrofitSecond()
    }
}
