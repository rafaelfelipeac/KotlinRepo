package com.rafaelfelipeac.marvelapp.features.details.domain.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl(imageType: ImageType) = when (imageType) {
        ImageType.STANDARD -> "$path/standard_amazing.$extension"
        ImageType.LANDSCAPE -> "$path/landscape_incredible.$extension"
        ImageType.PORTRAIT -> "$path/portrait_incredible.$extension"
    }

    enum class ImageType {
        STANDARD, LANDSCAPE, PORTRAIT
    }
}
