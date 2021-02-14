package com.rafaelfelipeac.marvelapp.features.characters.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

interface CharactersRepository {

    suspend fun getCharacters(
            apiKey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<Character>>
}
