package com.sknikod.standapp.domain.use_case

import com.sknikod.standapp.data.remote.repository.StandappRepositoryImpl
import javax.inject.Inject


class GetArticle (
    private val repo: StandappRepositoryImpl
)
{
    suspend operator fun invoke(id:Int?=null)=repo.getArticle(id)
}