package com.rafaelfelipeac.marvelapp.features.favorites.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

interface FavoriteRepository {

    suspend fun getFavorites(language: String, sort: String, page: Int): ResultWrapper<List<Character>>
}
