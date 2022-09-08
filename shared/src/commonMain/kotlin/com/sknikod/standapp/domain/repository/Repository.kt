package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.uti.Result

interface Repository {
    val client: RestApiClient
    suspend fun getListOfProjects(): Result<List<Project>>
    suspend fun getProject(id: Int): Result<Project>
    suspend fun getListOfArticles(): Result<Article>
    suspend fun getArticles(id: Int): Result<List<Article>>
    suspend fun getImage(path: String): Result<ByteArray>
}
