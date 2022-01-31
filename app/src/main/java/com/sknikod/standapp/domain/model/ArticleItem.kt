package com.sknikod.standapp.domain.model

data class ArticleItem(
    val alias: String,
    val authors: List<Any>,
    val comments_number: Int,
    val creation_date: String,
    val creator: Creator,
    val gallery: List<Any>,
    val id: Int,
    val links: List<Any>,
    val publication_date: String,
    val tags: List<Any>,
    val text: String,
    val title: String
)