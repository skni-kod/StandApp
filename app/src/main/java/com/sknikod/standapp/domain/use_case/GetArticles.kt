package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import javax.inject.Inject

class GetArticles (
    private val repo: StandappRepositoryImpl
)
{
    suspend operator fun invoke()=repo.getArticles()
}