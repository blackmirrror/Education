package ru.blackmirrror.dagger2

import android.app.Application
import ru.blackmirrror.dagger2.di.AppComponent
import ru.blackmirrror.dagger2.di.DaggerAppComponent

class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}