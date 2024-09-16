package ru.blackmirrror.androidsdk

import android.app.Application
import org.koin.core.context.startKoin
import ru.blackmirrror.androidsdk.di.appModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}