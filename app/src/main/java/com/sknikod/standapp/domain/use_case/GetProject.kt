package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import com.sknikod.standapp.domain.repository.StandappRepository
import javax.inject.Inject

class GetProject(
    private val repo: StandappRepository
)
{
    suspend operator fun invoke(id:Int?=null)=repo.getProject(id)
}