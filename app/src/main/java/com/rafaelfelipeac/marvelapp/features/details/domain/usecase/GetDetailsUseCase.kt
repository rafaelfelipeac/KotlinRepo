package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(apikey: String, hash: String, ts: Long): ResultWrapper<List<Character>> {
        return detailsRepository.getDetails(apikey, hash, ts)
    }
}
