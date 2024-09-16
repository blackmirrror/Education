package ru.blackmirrror.rxjavaexamples.data

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.blackmirrror.rxjavaexamples.data.models.SearchResponse

interface ApiService {

    @GET("films/search-by-keyword")
    fun getFilmsByKeyword(
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 1
        ): Observable<SearchResponse>
}