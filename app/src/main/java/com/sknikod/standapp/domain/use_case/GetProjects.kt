package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import com.sknikod.standapp.domain.repository.StandappRepository

class GetProjects(
    private val repo: StandappRepository
)
{
    suspend operator fun invoke()=repo.getProjects()
}