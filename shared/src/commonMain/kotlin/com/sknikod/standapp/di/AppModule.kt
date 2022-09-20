package com.sknikod.standapp.di

import com.sknikod.standapp.data.client.RestApiClientKtorImpl
import com.sknikod.standapp.data.repository.RepositoryArticleImpl
import com.sknikod.standapp.data.repository.RepositoryImageImpl
import com.sknikod.standapp.data.repository.RepositoryProjectImpl
import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.domain.repository.RepositoryArticle
import com.sknikod.standapp.domain.repository.RepositoryImage
import com.sknikod.standapp.domain.repository.RepositoryProject
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {
    private val client = module {

        factory { RestApiClientKtorImpl() as RestApiClient}
        factory { RepositoryProjectImpl(get()) as RepositoryProject}
        factory { RepositoryArticleImpl(get()) as RepositoryArticle}
        factory { RepositoryImageImpl(get()) as RepositoryImage }
    }
    val modules = listOf<Module>(client)
}
