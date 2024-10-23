package ru.blackmirrror.dagger2.home.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.blackmirrror.dagger2.home.HomeFragment
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@HomeScope
@Component(
    modules = [HomeModule::class, RepoModule::class, ViewModelModule::class],
)
internal interface HomeComponent {
    fun injectHomeFragment(homeFragment: HomeFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope()
