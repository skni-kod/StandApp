package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val description: String,
    val gallery: List<Gallery?>,
    val icon: String,
    val id: Int,
    val isVisible: Boolean,
    val name: String
)
