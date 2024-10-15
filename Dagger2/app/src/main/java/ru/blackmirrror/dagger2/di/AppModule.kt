package ru.blackmirrror.dagger2.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.blackmirrror.dagger2.core.di.CoreComponent
import ru.blackmirrror.dagger2.core.di.First
import ru.blackmirrror.dagger2.core.di.Second
import javax.inject.Singleton

@Module
class AppModule {

    @[Provides Singleton First]
    fun provideRetrofitFirst(context: Context): Retrofit {
        return CoreComponent.init(context).getFirstRetrofit()
    }

    @[Provides Singleton Second]
    fun provideRetrofitSecond(context: Context): Retrofit {
        return CoreComponent.init(context).getSecondRetrofit()
    }
}