package ru.blackmirrror.dagger2.home.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.blackmirrror.dagger2.home.HomeFragment
import javax.inject.Qualifier
import javax.inject.Scope

@Home
@Component(
    modules = [HomeDataModule::class, HomeDomainModule::class, HomePresentationModule::class],
    dependencies = [HomeDependenciesProvider::class]
)
interface HomeComponent {

    val deps: HomeDependenciesProvider

    @Component.Builder
    interface Builder {

        fun homeDependenciesProvider(
            homeDependenciesProvider: HomeDependenciesProvider
        ): Builder

        fun build(): HomeComponent
    }

    companion object {
        @Volatile
        private var homeComponent: HomeComponent? = null

        @Synchronized
        fun init(context: Context): HomeComponent {
            if (homeComponent == null) {
                val deps = context.applicationContext as HomeDependenciesProvider
                homeComponent = DaggerHomeComponent.builder()
                    .homeDependenciesProvider(deps)
                    .build()
            }
            return homeComponent!!
        }
    }

    fun inject(fragment: HomeFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class Home
