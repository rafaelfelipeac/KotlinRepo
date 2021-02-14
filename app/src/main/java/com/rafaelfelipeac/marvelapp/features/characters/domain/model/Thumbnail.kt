package com.rafaelfelipeac.marvelapp.features.characters.domain.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl() = "$path/landscape_xlarge.$extension"
}
