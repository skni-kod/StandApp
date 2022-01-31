package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.domain.model.ProjectList
import com.sknikod.standapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

abstract class StandappRepository {
    abstract suspend fun getProject(id:Int?=null):NetworkResult<ProjectItem>
    abstract fun getProjects(): Flow<NetworkResult<ProjectList>>
    suspend fun <T> apiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return NetworkResult.Error(data = response.body(),
                message = "Oops, something went wrong:${response.message()}"
            )
        } catch (e: Exception) {
            return NetworkResult.Error(
                message = "Oops, something went wrong:${e.message}"
            )
        }
    }
}