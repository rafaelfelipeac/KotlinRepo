package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(favorite: Favorite) {
        return favoriteRepository.delete(favorite)
    }
}
