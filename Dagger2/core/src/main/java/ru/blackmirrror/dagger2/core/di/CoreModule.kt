package ru.blackmirrror.dagger2.core.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CoreModule {

    @[Provides First Core]
    fun provideApiServiceFirst(): Retrofit {
        return newRetrofitInstance()
    }

    @[Provides Second Core]
    fun provideApiServiceSecond(): Retrofit {
        return newRetrofitInstance()
    }

    private fun newRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/v2.2/")
            .client(makeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeApiKeyInterceptor())
            .build()
    }

    private fun makeApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("x-api-key", "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
                .build()
            chain.proceed(newRequest)
        }
    }
}