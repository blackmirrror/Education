package ru.blackmirrror.dagger2.core.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Singleton
    @Provides
    fun getOkHttp(): OkHttpClient = OkHttpClient.Builder().build()
}