package com.sknikod.standapp.domain.model

data class ProjectItem(
    val authors: List<Author>,
    val creation_date: String,
    val creator: Creator,
    val gallery: List<Gallery>,
    val id: Int,
    val links: List<Any>,
    val publication_date: String,
    val section: Section,
    val text: String,
    val title: String
)