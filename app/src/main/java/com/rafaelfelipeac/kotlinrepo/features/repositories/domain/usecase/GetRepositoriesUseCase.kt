package com.rafaelfelipeac.kotlinrepo.features.repositories.domain.usecase

import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.kotlinrepo.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository
) {
    suspend operator fun invoke(language: String, sort: String, page: Int): ResultWrapper<List<Repository>> {
        return repositoriesRepository.getRepositories(language, sort, page)
    }
}
