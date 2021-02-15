package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.FavoriteRepository
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(
        characterId: Long?,
        characterName: String?,
        characterUrl: String?
    ): Long {
        return favoriteRepository.save(
            Favorite(
                characterId ?: 0,
                characterName ?: "",
                characterUrl ?: ""
            )
        )
    }
}
