package com.example.androidmoviessample

import android.app.Application
import com.example.androidmoviessample.di.dataModule
import com.example.androidmoviessample.di.domainModule
import com.example.androidmoviessample.di.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(uiModules, domainModule, dataModule)
        }
    }
}