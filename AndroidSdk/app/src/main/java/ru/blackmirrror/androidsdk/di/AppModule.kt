package ru.blackmirrror.androidsdk.di

import androidx.fragment.app.FragmentActivity
import org.koin.dsl.module
import ru.blackmirrror.androidsdk.AppRouter
import ru.blackmirrror.core.Router

val appModule = module {
    single<Router> { (activity: FragmentActivity) -> AppRouter(activity.supportFragmentManager) }
}