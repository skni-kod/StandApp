package com.sknikod.standapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    val id: Int,
    @SerialName("last_name")
    val lastName: String,
    val profile: Profile,
    val username: String
)