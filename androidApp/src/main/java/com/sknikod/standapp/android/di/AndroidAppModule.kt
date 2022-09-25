package com.sknikod.standapp.android.di

import com.sknikod.standapp.android.ui.account.AccountViewModel
import com.sknikod.standapp.android.ui.articlesNews.ArticleViewModel
import com.sknikod.standapp.android.ui.articlesNews.NewsViewModel
import com.sknikod.standapp.android.ui.projects.ProjectViewModel
import com.sknikod.standapp.data.client.RestApiClientKtorImpl
import com.sknikod.standapp.data.repository.RepositoryArticleImpl
import com.sknikod.standapp.data.repository.RepositoryImageImpl
import com.sknikod.standapp.data.repository.RepositoryProjectImpl
import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.repository.RepositoryArticle
import com.sknikod.standapp.domain.repository.RepositoryImage
import com.sknikod.standapp.domain.repository.RepositoryProject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AndroidAppModule {
    private val client = module {
        viewModelOf(::ProjectViewModel)
        viewModelOf(::NewsViewModel)
        viewModelOf(::AccountViewModel)
        viewModelOf(::ArticleViewModel)

    }
    val modules = listOf<Module>(client)
}
