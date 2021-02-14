package com.rafaelfelipeac.marvelapp.features.details.domain.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl() = "$path/landscape_xlarge.$extension"
}
