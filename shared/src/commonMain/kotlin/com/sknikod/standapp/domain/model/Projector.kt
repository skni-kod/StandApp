package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Projector(
    val description: String?,
    val id: Int,
    val index_number: Int?,
    val links: List<String?>
)