package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.domain.model.Projector

interface Repository {
    val client: RestApiClient
    suspend fun getListOfProjects(): List<Project>
    suspend fun getProject(id: Int): Project
}
