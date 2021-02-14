package com.rafaelfelipeac.marvelapp.features.details.data

import com.rafaelfelipeac.marvelapp.core.network.Network
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.data.model.CharacterDetailDtoMapper
import com.rafaelfelipeac.marvelapp.features.details.data.model.DetailInfoDtoMapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsDataSource @Inject constructor(
    private val detailsApi: DetailsApi,
    private val characterDetailDtoMapper: CharacterDetailDtoMapper,
    private val detailInfoDtoMapper: DetailInfoDtoMapper
) : DetailsRepository {

    override suspend fun getDetails(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<CharacterDetail> {
        return Network.request() {
            characterDetailDtoMapper.map(
                detailsApi.getDetails(
                    characterId,
                    apikey,
                    hash,
                    ts
                ).data.items.first()
            )
        }
    }

    override suspend fun getDetailsComics(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<DetailInfo>> {
        return Network.request() {
            detailsApi.getDetailsComics(characterId, apikey, hash, ts).data.items
                .map { detailInfoDtoMapper.map(it) }
        }
    }

    override suspend fun getDetailsSeries(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<DetailInfo>> {
        return Network.request() {
            detailsApi.getDetailsSeries(characterId, apikey, hash, ts).data.items
                .map { detailInfoDtoMapper.map(it) }
        }
    }
}
