package ru.blackmirrror.dagger2.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.blackmirrror.dagger2.core.di.AppViewModelFactory
import ru.blackmirrror.dagger2.core.di.ViewModelKey
import ru.blackmirrror.dagger2.home.presentation.HomeViewModel

@Module
internal interface ViewModelModule {

    @[Binds HomeScope]
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap ViewModelKey(HomeViewModel::class) HomeScope]
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}