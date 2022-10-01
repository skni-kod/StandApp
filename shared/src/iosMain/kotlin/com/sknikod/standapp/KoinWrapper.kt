package com.sknikod.standapp

import com.sknikod.standapp.di.AppModule
import com.sknikod.standapp.domain.repository.RepositoryArticle
import com.sknikod.standapp.domain.repository.RepositoryImage
import com.sknikod.standapp.domain.repository.RepositoryProject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinWrapper : KoinComponent {
    private val repositoryProject: RepositoryProject by inject()
    private val repositoryArticle: RepositoryArticle by inject()
    private val repositoryImage: RepositoryImage by inject()

    fun getRepositoryProject() = repositoryProject
    fun getRepositoryArticle() = repositoryArticle
    fun getRepositoryImage() = repositoryImage

    suspend fun greet() = repositoryProject.getListOfProjects()
    companion object {
        fun initKoin() {
            startKoin {
                modules(AppModule.modules)
            }
        }
    }
}
