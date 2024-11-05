package ru.blackmirrror.dagger2.core.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Singleton

private const val BASE_URL = "https://run.second-fake-api.io/"

@Module(includes = [NetworkModule::class])
internal class SecondNetworkModule {

    @Singleton
    @Provides
    fun provideApi(provideOkHttpClient: OkHttpClient): SecondApi =
        Retrofit.Builder().baseUrl(BASE_URL).client(provideOkHttpClient).build()
            .create(SecondApi::class.java)
}

interface SecondApi {

    @GET
    suspend fun getSecondResult(): Any
}