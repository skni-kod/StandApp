package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val authors: List<Author>,
    val creation_date: String,
    val creator: Creator,
    val gallery: List<Gallery>,
    val id: Int,
    val links: List<String?>,
    val publication_date: String,
    val section: Section,
    val text: String,
    val title: String
)