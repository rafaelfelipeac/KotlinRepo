package com.rafaelfelipeac.marvelapp.features.details.data

import com.rafaelfelipeac.marvelapp.core.network.Network
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDtoMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsDataSource @Inject constructor(
    private val detailsApi: DetailsApi,
    private val detailsDtoMapper: CharacterDtoMapper
) : DetailsRepository {

    override suspend fun getDetails(
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            detailsApi.getDetails(apikey, hash, ts).items
                .map { detailsDtoMapper.map(it) }
        }
    }

    override suspend fun getDetailsComics(
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            detailsApi.getDetailsComics(apikey, hash, ts).items
                .map { detailsDtoMapper.map(it) }
        }
    }

    override suspend fun getDetailsSeries(
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<Character>> {
        return Network.request() {
            detailsApi.getDetailsSeries(apikey, hash, ts).items
                .map { detailsDtoMapper.map(it) }
        }
    }
}
