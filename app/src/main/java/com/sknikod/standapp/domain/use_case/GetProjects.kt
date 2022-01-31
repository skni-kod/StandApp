package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl

class GetProjects(
    private val repo:StandappRepositoryImpl)
{
    suspend operator fun invoke()=repo.getProjects()
}