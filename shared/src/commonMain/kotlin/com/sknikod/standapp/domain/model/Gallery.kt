package com.sknikod.standapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    @SerialName("gallery_visibility")
    val galleryVisibility: Boolean,
    val id: Int,
    val image: String,
    @SerialName("text_visibility")
    val textVisibility: Boolean,
    val thumbnail: String,
    @SerialName("thumbnail_visibility")
    val thumbnailVisibility: Boolean
)
