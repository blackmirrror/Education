package ru.blackmirrror.dagger2.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.core.di.First
import ru.blackmirrror.dagger2.core.di.Second
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @First
    fun getRetrofitFirst(): Retrofit

    @Second
    fun getRetrofitSecond(): Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope