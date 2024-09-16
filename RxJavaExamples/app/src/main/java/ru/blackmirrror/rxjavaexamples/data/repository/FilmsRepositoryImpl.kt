package ru.blackmirrror.rxjavaexamples.data.repository

import io.reactivex.rxjava3.core.Observable
import ru.blackmirrror.rxjavaexamples.data.ApiService
import ru.blackmirrror.rxjavaexamples.domain.models.Film
import ru.blackmirrror.rxjavaexamples.domain.models.toFilm
import ru.blackmirrror.rxjavaexamples.domain.repository.FilmsRepository

class FilmsRepositoryImpl(private val apiService: ApiService): FilmsRepository {

    override fun getFilmsByKeyword(keyword: String): Observable<List<Film>> {
        return apiService.getFilmsByKeyword(keyword)
            .map { response ->
                response.films.map {
                    it.toFilm()
                }
            }
    }

}