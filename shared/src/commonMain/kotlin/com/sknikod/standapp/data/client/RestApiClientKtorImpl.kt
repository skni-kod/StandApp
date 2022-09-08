package com.sknikod.standapp.data.client

import com.sknikod.standapp.domain.client.KtorClient
import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.uti.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*

class RestApiClientKtorImpl : RestApiClient, KtorClient {
    override val client: HttpClient
    override val clientToken: HttpClient
    constructor(client: HttpClientEngine, clientToken: HttpClientEngine,) {
        this.client = HttpClient(client) {
            install(ContentNegotiation) {
                json()
            }
        }
        this.clientToken = HttpClient(clientToken) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
    constructor() {
        client = HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }
        clientToken = HttpClient() {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    override suspend fun getListOfProjects(): HttpResponse = client.get(Const.ApiUrl.BACKEND.projects().content)

    override suspend fun getProject(id: Int): HttpResponse =
        client.get(Const.ApiUrl.BACKEND.projects().specified(id).content)

    override suspend fun getListOfArticles(): HttpResponse = client.get(Const.ApiUrl.BACKEND.articles().content)

    override suspend fun getArticles(id: Int): HttpResponse =
        client.get(Const.ApiUrl.BACKEND.articles().specified(id).content)

    override suspend fun getImage(path: String): HttpResponse =
        client.get(Const.ApiUrl.MAIN.image(path).content)
}
