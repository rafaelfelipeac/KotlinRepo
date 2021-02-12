package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.favorites.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(language: String, sort: String, page: Int): ResultWrapper<List<Character>> {
        return favoriteRepository.getFavorites(language, sort, page)
    }
}
