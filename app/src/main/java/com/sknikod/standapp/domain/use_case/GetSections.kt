package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.domain.repository.StandappRepository

class GetSections(

    private val repo: StandappRepository
)
{
    suspend operator fun invoke()=repo.getSections()
}