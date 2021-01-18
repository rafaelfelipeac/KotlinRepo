package com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.usecase

import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.kotlinrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.model.Repository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository
) {
    suspend operator fun invoke(language: String, sort: String, page: Int): ResultWrapper<List<Repository>> {
        return repositoriesRepository.getRepositories(language, sort, page)
    }
}
