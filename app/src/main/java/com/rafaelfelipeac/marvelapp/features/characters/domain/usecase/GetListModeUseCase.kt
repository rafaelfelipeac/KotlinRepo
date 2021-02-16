package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import javax.inject.Inject

class GetListModeUseCase @Inject constructor(
    private val listModeRepository: ListModeRepository
) {
    suspend operator fun invoke(): Boolean {
        return listModeRepository.getListMode()
    }
}
