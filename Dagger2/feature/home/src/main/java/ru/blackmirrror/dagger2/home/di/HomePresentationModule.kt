package ru.blackmirrror.dagger2.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.blackmirrror.dagger2.core.di.AppViewModelFactory
import ru.blackmirrror.dagger2.home.domain.HomeRepository
import ru.blackmirrror.dagger2.home.presentation.HomeViewModel
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
interface HomePresentationModule {

    @[Binds Home]
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @[Binds IntoMap ViewModelKey(HomeViewModel::class) Home]
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)