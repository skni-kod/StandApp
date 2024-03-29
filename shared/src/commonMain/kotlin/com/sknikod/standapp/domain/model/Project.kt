package com.sknikod.standapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val authors: List<Author>,
    @SerialName("creation_date")
    val creationDate: String,
    val creator: Creator,
    val gallery: List<Gallery>,
    val id: Int,
    val links: List<String?>,
    @SerialName("publication_date")
    val publicationDate: String,
    val section: Section,
    val text: String,
    val title: String
)