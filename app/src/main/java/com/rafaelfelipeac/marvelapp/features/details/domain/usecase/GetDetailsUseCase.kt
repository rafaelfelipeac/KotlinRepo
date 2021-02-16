package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(
        characterId: Long,
        apikey: String,
        ts: Long,
        hash: String
    ): ResultWrapper<CharacterDetail> {
        return detailsRepository.getDetails(characterId, apikey, hash, ts)
    }
}
