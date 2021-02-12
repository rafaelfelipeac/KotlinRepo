package com.rafaelfelipeac.marvelapp.features.characters.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(language: String, sort: String, page: Int): ResultWrapper<List<Character>>
}
