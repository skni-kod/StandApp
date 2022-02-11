package com.sknikod.standapp.data.remote.repository

import com.sknikod.standapp.data.remote.StandappApi
import com.sknikod.standapp.domain.model.ArticleItem
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.Section
import com.sknikod.standapp.domain.repository.StandappRepository
import com.sknikod.standapp.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StandappRepositoryImpl @Inject constructor(
    private val api:StandappApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): StandappRepository()
{
    override suspend fun getProject(id: Int?): NetworkResult<ProjectItem> =withContext(ioDispatcher){
        return@withContext apiCall { api.getProject(id) }
    }
    override suspend fun getProjects(): NetworkResult<List<ProjectItem>> =withContext(ioDispatcher){
        return@withContext apiCall { api.getProjectsList()}
    }

    override suspend fun getArticles():  NetworkResult<List<ArticleItem>> =withContext(ioDispatcher){
        return@withContext    apiCall { api.getArticlesList() }    }

    override suspend fun getArticle(id: Int?): NetworkResult<ArticleItem>  =withContext(ioDispatcher){
        return@withContext    apiCall { api.getArticle(id) }    }

    override suspend fun getSections(): NetworkResult<List<Section>>  =withContext(ioDispatcher){
        return@withContext    apiCall { api.getSections() }    }
}

