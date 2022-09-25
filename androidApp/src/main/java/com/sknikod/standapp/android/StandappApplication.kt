package com.sknikod.standapp.android

import android.app.Application
import com.sknikod.standapp.android.di.AndroidAppModule
import com.sknikod.standapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class StandappApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StandappApplication)
            androidLogger()
            modules(AppModule.modules+AndroidAppModule.modules)
        }
    }
}