package com.sknikod.standapp.data.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.repository.Repository
import com.sknikod.standapp.domain.repository.RepositoryImage
import com.sknikod.standapp.uti.Result
import com.sknikod.standapp.uti.simplifyFetchKtor
import io.ktor.client.call.*

class RepositoryImageImpl(client: RestApiClient) : Repository(client), RepositoryImage {
    override suspend fun getImage(path: String): Result<ByteArray> {
        return simplifyFetchKtor {
            client.getImage(path).body()
        }
    }
}
