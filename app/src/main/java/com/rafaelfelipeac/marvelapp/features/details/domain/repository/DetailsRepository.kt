package com.rafaelfelipeac.marvelapp.features.details.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character

interface DetailsRepository {

    suspend fun getDetails(apikey: String, hash: String, ts: Long): ResultWrapper<List<Character>>

    suspend fun getDetailsComics(apikey: String, hash: String, ts: Long): ResultWrapper<List<Character>>

    suspend fun getDetailsSeries(apikey: String, hash: String, ts: Long): ResultWrapper<List<Character>>
}
