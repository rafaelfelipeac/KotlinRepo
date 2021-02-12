package com.rafaelfelipeac.marvelapp.features.favorites.data

import com.rafaelfelipeac.marvelapp.core.network.Network
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDtoMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import com.rafaelfelipeac.marvelapp.features.favorites.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteDataSource @Inject constructor(
    private val favoriteApi: FavoriteDao,
    private val favoriteDtoMapper: CharacterDtoMapper
) : FavoriteRepository {

    override suspend fun getFavorites(
        language: String,
        sort: String,
        page: Int
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            favoriteApi.getFavorite(language, sort, page).items
                .map { favoriteDtoMapper.map(it) }
        }
    }
}
