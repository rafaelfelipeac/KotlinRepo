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
        language: String,
        sort: String,
        page: Int
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            charactersApi.getAllCharacters(language, sort, page).items
                .map { characterDtoMapper.map(it) }
        }
    }
}
