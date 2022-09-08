package com.sknikod.standapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val description: String?,
    val id: Int,
    @SerialName("index_number")
    val indexNumber: Int?,
    val links: List<String?>
)
