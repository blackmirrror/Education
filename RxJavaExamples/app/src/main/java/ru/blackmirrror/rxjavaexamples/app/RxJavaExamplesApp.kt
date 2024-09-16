package ru.blackmirrror.rxjavaexamples.app

import android.app.Application
import org.koin.core.context.startKoin
import ru.blackmirrror.rxjavaexamples.app.di.appModule
import ru.blackmirrror.rxjavaexamples.app.di.dataModule

class RxJavaExamplesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, dataModule)
        }
    }
}