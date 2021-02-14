package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(
        apiKey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<Character>> {
        return charactersRepository.getCharacters(apiKey, hash, ts)
    }
}
