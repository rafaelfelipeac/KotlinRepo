package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsComicsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(
        characterId: Long,
        apikey: String,
        hash: String,
        ts: Long
    ): ResultWrapper<List<DetailInfo>> {
        return detailsRepository.getDetailsComics(characterId, apikey, hash, ts)
    }
}
