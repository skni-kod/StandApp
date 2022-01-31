package com.sknikod.standapp.data.remote

import com.sknikod.standapp.domain.model.ProjectList
import com.sknikod.standapp.domain.model.ProjectItem
import com.sknikod.standapp.util.SpecifedBottomNavigationItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StandappApi {
    @GET("projects")
    suspend fun getProjectsList(): Response<ProjectList>
    @GET("projects/{id}")
    suspend fun getProject(@Path("id")id:Int?):Response<ProjectItem>

    companion object {
        const val BASE_URL =""
    }
}