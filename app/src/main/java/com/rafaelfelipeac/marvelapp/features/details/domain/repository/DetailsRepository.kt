package com.rafaelfelipeac.marvelapp.features.details.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo

interface DetailsRepository {

    suspend fun getDetails(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<CharacterDetail>

    suspend fun getDetailsComics(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<DetailInfo>>

    suspend fun getDetailsSeries(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long,
        offset: Int
    ): ResultWrapper<List<DetailInfo>>
}
