package com.rafaelfelipeac.marvelapp.features.characters.domain.model

data class CharacterList(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)
