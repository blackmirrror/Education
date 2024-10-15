package ru.blackmirrror.dagger2

import android.app.Application
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.core.di.DaggerCoreComponent
import ru.blackmirrror.dagger2.di.DaggerAppComponent
import ru.blackmirrror.dagger2.home.di.HomeDependenciesProvider

class App: Application(),
    HomeDependenciesProvider
{

    private val appComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun getRetrofitFirst(): Retrofit {
        return appComponent.getRetrofitFirst()
    }

    override fun getRetrofitSecond(): Retrofit {
        return appComponent.getRetrofitSecond()
    }
}