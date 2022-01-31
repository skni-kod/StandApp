package com.sknikod.standapp.data.remote

import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.ArticleItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StandappApi {
    @GET("projects")
    suspend fun getProjectsList(): Response<List<ProjectItem>>
    @GET("projects/{id}")
    suspend fun getProject(@Path("id")id:Int?):Response<ProjectItem>
    @GET("articles/{id}")
    suspend fun getArticle(@Path("id")id:Int?):Response<ArticleItem>
    @GET("articles")
    suspend fun getArticlesList():Response<List<ArticleItem>>

    companion object {
        const val BASE_URL =""
    }
}