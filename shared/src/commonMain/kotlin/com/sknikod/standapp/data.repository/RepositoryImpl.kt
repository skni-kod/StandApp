package com.sknikod.standapp.data.repository

import com.sknikod.standapp.domain.client.RestApiClient
import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.domain.model.Projector
import com.sknikod.standapp.domain.repository.Repository
import io.ktor.client.call.*

class RepositoryImpl(override val client: RestApiClient) : Repository {
    override suspend fun getListOfProjects(): List<Project> {
       return client.getListOfProjects().body()

    }

    override suspend fun getProject(id: Int): Project {
       return client.getProject(id).body()
    }
}