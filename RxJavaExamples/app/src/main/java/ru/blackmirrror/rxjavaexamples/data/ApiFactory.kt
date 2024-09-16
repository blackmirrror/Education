package ru.blackmirrror.rxjavaexamples.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.1/"
    private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(makeApiKeyInterceptor())
        .addInterceptor(makeLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun makeApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("x-api-key", API_KEY)
                .build()
            try {
                chain.proceed(newRequest)
            } catch (e: SocketTimeoutException) {
                throw IOException("SocketTimeoutException")
            }
        }
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .addCallAdapterFactory(
                RxJava3CallAdapterFactory.create()
            )
            .build()
    }

    fun create(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }
}