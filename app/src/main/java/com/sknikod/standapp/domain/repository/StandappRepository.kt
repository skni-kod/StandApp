package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.model.ArticleItem
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.Section
import com.sknikod.standapp.util.NetworkResult
import retrofit2.Response

abstract class StandappRepository {
    abstract suspend fun getProject(id:Int?=null):NetworkResult<ProjectItem>
    abstract suspend fun getProjects(): NetworkResult<List<ProjectItem>>
    abstract suspend fun getArticles():  NetworkResult<List<ArticleItem>>
    abstract suspend fun getArticle(id:Int?=null): NetworkResult<ArticleItem>
    abstract suspend fun getSections(): NetworkResult<List<Section>>

    suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return NetworkResult.Error(data = response.body(),
                exception = Exception("Oops, something went wrong:${response.message()}")
            )
        } catch (e: Exception) {
            return NetworkResult.Error(
                exception = Exception("Oops, something went wrong:${e.message}")
            )
        }
    }
}