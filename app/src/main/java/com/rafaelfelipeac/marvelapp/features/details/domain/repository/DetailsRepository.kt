package com.rafaelfelipeac.marvelapp.features.details.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

interface DetailsRepository {

    suspend fun getDetails(language: String, sort: String, page: Int): ResultWrapper<List<Character>>
}
