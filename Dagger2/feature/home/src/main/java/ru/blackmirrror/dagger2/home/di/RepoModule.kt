package ru.blackmirrror.dagger2.home.di

import dagger.Binds
import dagger.Module
import ru.blackmirrror.dagger2.home.data.repository.FirstRepoImpl
import ru.blackmirrror.dagger2.home.data.repository.SecondRepoImpl
import ru.blackmirrror.dagger2.home.domain.FirstRepository
import ru.blackmirrror.dagger2.home.domain.SecondRepository

@Module
internal interface RepoModule {

    @HomeScope
    @Binds
    fun firstRepo(impl: FirstRepoImpl): FirstRepository

    @HomeScope
    @Binds
    fun secondRepo(impl: SecondRepoImpl): SecondRepository
}
