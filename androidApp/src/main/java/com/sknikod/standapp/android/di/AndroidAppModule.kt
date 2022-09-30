package com.sknikod.standapp.android.di

import com.sknikod.standapp.android.ui.account.AccountViewModel
import com.sknikod.standapp.android.ui.articles.ArticleViewModel
import com.sknikod.standapp.android.ui.news.NewsViewModel
import com.sknikod.standapp.android.ui.projects.ProjectViewModel
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
