package ru.blackmirrror.rxjavaexamples.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.blackmirrror.rxjavaexamples.domain.models.Film

interface FilmsRepository {

    fun getFilmsByKeyword(keyword: String): Observable<List<Film>>

}