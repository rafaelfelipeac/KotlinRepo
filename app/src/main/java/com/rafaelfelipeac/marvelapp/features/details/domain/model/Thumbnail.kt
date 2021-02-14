package com.rafaelfelipeac.marvelapp.features.details.domain.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl(isPortrait: Boolean = false) = if (isPortrait) {
        "$path/portrait_xlarge.$extension"
    } else {
        "$path/landscape_xlarge.$extension"
    }
}
