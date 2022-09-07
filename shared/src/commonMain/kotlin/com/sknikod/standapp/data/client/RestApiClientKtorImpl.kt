package com.sknikod.standapp.data.client

import com.sknikod.standapp.domain.client.KtorClient
import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.uti.Const
import com.sknikod.standapp.uti.projects
import com.sknikod.standapp.uti.specified
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

class RestApiClientKtorImpl : RestApiClient, KtorClient {
    override val client: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }
    override val clientToken: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }
    override suspend fun getListOfProjects(): HttpResponse = client.get(Const.ApiUrl.BACKEND.projects().content)

    override suspend fun getProject(id: Int): HttpResponse = client.get(Const.ApiUrl.BACKEND.projects().specified(id).content)
}
