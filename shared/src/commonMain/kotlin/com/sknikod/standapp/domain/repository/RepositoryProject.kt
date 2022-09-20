package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.model.Project
import com.sknikod.standapp.uti.Result

interface RepositoryProject {
    suspend fun getListOfProjects(): Result<List<Project>>
    suspend fun getProject(id: Int): Result<Project>
}