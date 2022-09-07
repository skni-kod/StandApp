package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val description: String?,
    val id: Int,
    val index_number: Int?,
    val links: List<String?>
)