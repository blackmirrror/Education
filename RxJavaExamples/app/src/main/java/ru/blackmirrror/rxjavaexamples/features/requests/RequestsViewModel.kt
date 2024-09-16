package ru.blackmirrror.rxjavaexamples.features.requests

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.blackmirrror.rxjavaexamples.domain.models.DiscountCard
import ru.blackmirrror.rxjavaexamples.domain.repository.DiscountCardsRepository
import ru.blackmirrror.rxjavaexamples.domain.repository.FilmsRepository

@SuppressLint("CheckResult")
class RequestsViewModel(private val discountCardsRepository: DiscountCardsRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _discountCards = BehaviorSubject.create<List<DiscountCard>>()
    val discountCards: Observable<List<DiscountCard>> = _discountCards.hide()

    private val _clicked = BehaviorSubject.createDefault(true)
    val clicked: Observable<Boolean> = _clicked.hide()

    init {
        getDiscountCards()
    }

    private fun getDiscountCards() {
        val moviesDisposable = discountCardsRepository.getDiscountCards(!_clicked.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { films ->
                    _discountCards.onNext(films)
                },
                { throwable ->
                    throwable.printStackTrace()
                }
            )
        disposables.add(moviesDisposable)
    }

    fun changeClick() {
        _clicked.onNext(!_clicked.value!!)
        getDiscountCards()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}