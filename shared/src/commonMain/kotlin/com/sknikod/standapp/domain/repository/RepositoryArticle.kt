package com.sknikod.standapp.domain.repository

import com.sknikod.standapp.domain.model.Article
import com.sknikod.standapp.uti.Result

interface RepositoryArticle {
    suspend fun getListOfArticles(): Result<List<Article>>
    suspend fun getArticle(id: Int): Result<Article>
}
