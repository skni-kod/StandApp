package com.sknikod.standapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val gallery_visibility: Boolean,
    val id: Int,
    val image: String,
    val text_visibility: Boolean,
    val thumbnail: String,
    val thumbnail_visibility: Boolean
)
