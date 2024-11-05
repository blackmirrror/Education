package ru.blackmirrror.dagger2.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.core.network.FirstApi
import ru.blackmirrror.dagger2.core.network.FirstNetworkModule
import ru.blackmirrror.dagger2.core.network.SecondApi
import ru.blackmirrror.dagger2.core.network.SecondNetworkModule
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [FirstNetworkModule::class, SecondNetworkModule::class])
internal interface CoreComponent {

    val firstApi: FirstApi

    val secondApi: SecondApi
}