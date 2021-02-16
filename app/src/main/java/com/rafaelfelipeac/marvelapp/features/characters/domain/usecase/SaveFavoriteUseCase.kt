package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
        favorite: Favorite
    ): Long {
        return favoriteRepository.save(favorite)
    }
}
