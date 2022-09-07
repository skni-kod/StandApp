package com.sknikod.standapp.domain.client

import io.ktor.client.*

interface KtorClient {
    val client: HttpClient
    val clientToken: HttpClient
}
