package com.sknikod.standapp.domain.model

data class Section(
    val description: String,
    val gallery: List<Any>,
    val icon: String,
    val id: Int,
    val isVisible: Boolean,
    val name: String
)