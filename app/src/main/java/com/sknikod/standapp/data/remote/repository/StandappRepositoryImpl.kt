package com.sknikod.standapp.data.remote.repository

import com.sknikod.standapp.data.remote.StandappApi
import com.sknikod.standapp.domain.model.ArticleItem
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.repository.StandappRepository
import com.sknikod.standapp.util.NetworkResult
import javax.inject.Inject

class StandappRepositoryImpl @Inject constructor(
    private val api:StandappApi
): StandappRepository()
{
    override suspend fun getProject(id: Int?): NetworkResult<ProjectItem> {
        return   apiCall { api.getProject(id) }
    }
    override suspend fun getProjects(): NetworkResult<List<ProjectItem>> {
        return   apiCall { api.getProjectsList() }    }

    override suspend fun getArticles():  NetworkResult<List<ArticleItem>>{
        return   apiCall { api.getArticlesList() }    }

    override suspend fun getArticle(id: Int?): NetworkResult<ArticleItem> {
        return   apiCall { api.getArticle(id) }    }
}