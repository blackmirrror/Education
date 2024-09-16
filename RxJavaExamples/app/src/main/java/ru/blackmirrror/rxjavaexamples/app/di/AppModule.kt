package ru.blackmirrror.rxjavaexamples.app.di

import org.koin.dsl.module
import ru.blackmirrror.rxjavaexamples.features.requests.RequestsViewModel
import ru.blackmirrror.rxjavaexamples.features.search.SearchViewModel

val appModule = module {

    factory<SearchViewModel> {
        SearchViewModel(
            filmsRepository = get()
        )
    }

    factory<RequestsViewModel> {
        RequestsViewModel(
            discountCardsRepository = get()
        )
    }
}