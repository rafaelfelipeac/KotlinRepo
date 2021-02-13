package com.rafaelfelipeac.marvelapp.features.characters.data

import com.rafaelfelipeac.marvelapp.core.network.Network
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDtoMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersDataSource @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDtoMapper: CharacterDtoMapper
) : CharactersRepository {

    override suspend fun getCharacters(
        apiKey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            charactersApi.getAllCharacters(apiKey, hash, ts).data.items
                .map { characterDtoMapper.map(it) }
        }
    }
}
