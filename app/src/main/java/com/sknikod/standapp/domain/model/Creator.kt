package com.sknikod.standapp.domain.model

data class Creator(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val profile: Profile,
    val username: String
)