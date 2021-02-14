package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsSeriesUseCase @Inject constructor(
        private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(
            characterId: Long,
            apikey: String,
            hash: String,
            ts: Long,
            offset: Int
    ): ResultWrapper<List<DetailInfo>> {
        return detailsRepository.getDetailsSeries(characterId, apikey, hash, ts, offset)
    }
}
