package ru.blackmirrror.dagger2.home.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.blackmirrror.dagger2.home.domain.Movie

interface ApiServiceSecond {
    @GET("films/{id}")
    suspend fun getMovie(@Path("id") id: Int): Response<Movie>
}