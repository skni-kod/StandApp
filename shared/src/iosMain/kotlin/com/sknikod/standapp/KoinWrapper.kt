package com.sknikod.standapp

import com.sknikod.standapp.di.AppModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class KoinWrapper : KoinComponent {
    companion object {
        fun initKoin() {
            startKoin {
                modules(AppModule.modules)
            }
        }
    }
}
