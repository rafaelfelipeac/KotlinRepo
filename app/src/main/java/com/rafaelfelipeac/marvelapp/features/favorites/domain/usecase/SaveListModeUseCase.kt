package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import javax.inject.Inject

class SaveListModeUseCase @Inject constructor(
    private val listModeRepository: ListModeRepository
) {
    suspend operator fun invoke(listMode: Boolean) {
        return listModeRepository.saveListMode(listMode)
    }
}
