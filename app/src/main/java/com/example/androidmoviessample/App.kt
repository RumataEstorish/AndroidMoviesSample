package com.example.androidmoviessample

import android.app.Application
import com.example.androidmoviessample.di.dataModule
import com.example.androidmoviessample.di.domainModule
import com.example.androidmoviessample.di.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO plant external logging service tree
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(uiModules, domainModule, dataModule)
        }
    }
}