package com.sknikod.standapp.domain.client

import io.ktor.client.statement.*

interface RestApiClient {
    suspend fun getListOfProjects(): HttpResponse
    suspend fun getProject(id: Int): HttpResponse
}
