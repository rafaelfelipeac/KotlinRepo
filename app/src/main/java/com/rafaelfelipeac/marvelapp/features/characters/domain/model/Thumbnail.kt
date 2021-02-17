package com.rafaelfelipeac.marvelapp.features.characters.domain.model

import com.rafaelfelipeac.marvelapp.core.extension.convertToHttps

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getUrl() = "$path/landscape_incredible.$extension".convertToHttps()
}
