package ru.blackmirrror.dagger2.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Scope

@Core
@Component(modules = [CoreModule::class])
interface CoreComponent {

    @First
    fun getFirstRetrofit(): Retrofit

    @Second
    fun getSecondRetrofit(): Retrofit

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): CoreComponent
    }

    companion object {
        @Volatile
        private var coreComponent: CoreComponent? = null

        @Synchronized
        fun init(context: Context): CoreComponent {
            if (coreComponent == null) {
                coreComponent = DaggerCoreComponent.factory()
                    .create(context)
            }
            return coreComponent!!
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class First

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Second

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class Core