package com.sknikod.standapp.uti

import com.sknikod.standapp.domain.repository.Repository
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend inline fun <reified T> Repository.simplifyFetchKtor(
    dataSource: () -> HttpResponse
): Result<T> {
    return try {
        val content = dataSource()
        if (content.status == HttpStatusCode.OK || content.status == HttpStatusCode.Created) {
            Result.Success(content.body())
        } else {
            Result.FailureFetch(content)
        }
    } catch (e: NoTransformationFoundException) {
        Result.Error(e)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
