package ru.blackmirrror.dagger2.home.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.blackmirrror.dagger2.home.HomeFragment
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

@Home
@Singleton
@Component(
    modules = [HomeDataModule::class, HomeDomainModule::class, HomePresentationModule::class],
    dependencies = [HomeDependenciesProvider::class]
)
interface HomeComponent {

    val deps: HomeDependenciesProvider

    @Component.Factory
    interface Factory {

        fun create(
            homeDependenciesProvider: HomeDependenciesProvider
        ): HomeComponent
    }

    companion object {
        @Volatile
        private var homeComponent: HomeComponent? = null

        @Synchronized
        fun init(context: Context): HomeComponent {
            if (homeComponent == null) {
                val deps = context as HomeDependenciesProvider
                homeComponent = DaggerHomeComponent.factory()
                    .create(deps)
            }
            return homeComponent!!
        }
    }

    fun inject(fragment: HomeFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Home
