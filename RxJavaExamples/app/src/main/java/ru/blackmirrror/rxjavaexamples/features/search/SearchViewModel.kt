package ru.blackmirrror.rxjavaexamples.features.search

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.blackmirrror.rxjavaexamples.domain.models.Film
import ru.blackmirrror.rxjavaexamples.domain.repository.FilmsRepository
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
class SearchViewModel(private val filmsRepository: FilmsRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _movies = BehaviorSubject.create<List<Film>>()
    val movies: Observable<List<Film>> = _movies.hide()

    private val _time = BehaviorSubject.create<String>()
    val time: Observable<String> = _time.hide()

    val clickedMovie = PublishSubject.create<Int>()

    private val searchSubject = PublishSubject.create<String>()

    init {
        startTimer()
        searchSubject
            .debounce(1, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(Schedulers.io())
            .subscribe { keyword ->
                searchMovies(keyword)
            }
    }

    @SuppressLint("DefaultLocale")
    private fun startTimer() {
        val countdownTimeInSeconds = 60L

        val timerDisposable = Observable.interval(1, TimeUnit.SECONDS)
            .take(countdownTimeInSeconds + 1)
            .map { countdownTimeInSeconds - it }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { remainingTime ->
                val minutes = remainingTime / 60
                val seconds = remainingTime % 60
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                _time.onNext(formattedTime)

                if (remainingTime == 0L) {
                    _time.onNext("Finish!")
                }
            }

        disposables.add(timerDisposable)
    }

    fun onSearchQueryChanged(keyword: String) {
        searchSubject.onNext(keyword)
    }

    private fun searchMovies(keyword: String) {
        val moviesDisposable = filmsRepository.getFilmsByKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { films ->
                    _movies.onNext(films)
                },
                { throwable ->
                    throwable.printStackTrace()
                }
            )
        disposables.add(moviesDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        clickedMovie.onComplete()
    }
}