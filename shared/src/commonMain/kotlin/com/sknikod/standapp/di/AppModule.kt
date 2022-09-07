package com.sknikod.standapp.di

import com.sknikod.standapp.data.client.RestApiClientKtorImpl
import com.sknikod.standapp.data.repository.RepositoryImpl
import com.sknikod.standapp.domain.client.KtorClient
import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.repository.Repository
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object AppModule {
    private val client = module {
        factoryOf(::RestApiClientKtorImpl) {
            bind<RestApiClient>()
        }
        factory { RepositoryImpl(get()) as Repository}
    }
    val modules = listOf<Module>(client)
}
