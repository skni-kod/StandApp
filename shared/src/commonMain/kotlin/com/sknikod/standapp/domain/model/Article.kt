package com.sknikod.standapp.domain.model

import kotlinx.serialization.SerialName

data class Article(
    val alias: String,
    val authors: List<Author>,
    @SerialName("comments_number")
    val commentsNumber: Int,
    @SerialName("creation_date")
    val creationDate: String,
    val creator: Creator,
    val gallery: List<Gallery>,
    val group: String,
    val id: Int,
    val links: List<String>,
    @SerialName("publication_date")
    val publicationDate: String,
    val tags: List<Tag>,
    val text: String,
    val title: String
)
