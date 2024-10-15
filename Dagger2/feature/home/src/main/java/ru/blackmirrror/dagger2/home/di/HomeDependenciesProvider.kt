package ru.blackmirrror.dagger2.home.di

import retrofit2.Retrofit

interface HomeDependenciesProvider {
    fun getRetrofitFirst(): Retrofit
    fun getRetrofitSecond(): Retrofit
}