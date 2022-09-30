package com.sknikod.standapp.data.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.domain.repository.RepositoryArticle
import com.sknikod.standapp.uti.Result
import com.sknikod.standapp.uti.simplifyFetchKtor
import io.ktor.client.call.*

class RepositoryArticleImpl(client: RestApiClient) : Repository(client), RepositoryArticle {
    override suspend fun getListOfArticles(): Result<List<Article>> {
        return simplifyFetchKtor {
            client.getListOfArticles().body()
        }
    }

    override suspend fun getArticle(id: Int): Result<Article> {
        return simplifyFetchKtor {
            client.getArticles(id).body()
        }
    }
}
