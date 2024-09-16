package ru.blackmirrror.rxjavaexamples.domain.repository

import io.reactivex.rxjava3.core.Observable
import ru.blackmirrror.rxjavaexamples.domain.models.DiscountCard

interface DiscountCardsRepository {
    fun getDiscountCards(show: Boolean): Observable<List<DiscountCard>>
}