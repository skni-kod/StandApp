package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val profile: Projector,
    val username: String
)