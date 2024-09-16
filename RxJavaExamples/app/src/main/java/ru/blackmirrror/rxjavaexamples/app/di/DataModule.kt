package ru.blackmirrror.rxjavaexamples.app.di

import org.koin.dsl.module
import ru.blackmirrror.rxjavaexamples.data.ApiFactory
import ru.blackmirrror.rxjavaexamples.data.ApiService
import ru.blackmirrror.rxjavaexamples.data.repository.DiscountCardsRepositoryImpl
import ru.blackmirrror.rxjavaexamples.data.repository.FilmsRepositoryImpl
import ru.blackmirrror.rxjavaexamples.domain.repository.DiscountCardsRepository
import ru.blackmirrror.rxjavaexamples.domain.repository.FilmsRepository

val dataModule = module {

    single<ApiService> {
        ApiFactory.create()
    }

    single<FilmsRepository> {
        FilmsRepositoryImpl(
            apiService = get()
        )
    }

    single<DiscountCardsRepository> {
        DiscountCardsRepositoryImpl()
    }
}