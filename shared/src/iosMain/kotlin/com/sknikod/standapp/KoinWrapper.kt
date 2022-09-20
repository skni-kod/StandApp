package com.sknikod.standapp

import com.sknikod.standapp.di.AppModule
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.domain.repository.RepositoryProject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinWrapper : KoinComponent {
    val greeting: RepositoryProject by inject()
    suspend fun greet() = greeting.getListOfProjects()
    companion object {
        fun initKoin() {
            startKoin {
                modules(AppModule.modules)
            }
        }
    }
}
