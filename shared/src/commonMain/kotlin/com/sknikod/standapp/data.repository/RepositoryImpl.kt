package com.sknikod.standapp.data.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.uti.Result
import com.sknikod.standapp.uti.simplifyFetchKtor
import io.ktor.client.call.*

class RepositoryImpl(override val client: RestApiClient) : Repository {
    override suspend fun getListOfProjects(): Result<List<Project>> {
        return simplifyFetchKtor {
            client.getListOfProjects().body()
        }
    }

    override suspend fun getProject(id: Int): Result<Project> {
        return simplifyFetchKtor {
            client.getProject(id).body()
        }
    }
    override suspend fun getListOfArticles(): Result<Article> {
        return simplifyFetchKtor {

            client.getListOfArticles().body()
        }
    }

    override suspend fun getArticles(id: Int): Result<List<Article>> {
        return simplifyFetchKtor {

            client.getArticles(id).body()
        }
    }

    override suspend fun getImage(path: String): Result<ByteArray> {
        return simplifyFetchKtor {

            client.getImage(path).body()
        }
    }
}
