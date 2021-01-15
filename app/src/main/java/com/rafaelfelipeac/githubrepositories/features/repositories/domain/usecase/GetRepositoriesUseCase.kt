package com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase

import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryListDto
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository
) {
    suspend operator fun invoke(language: String, sort: String, page: Int): ResultWrapper<RepositoryListDto> {
        return repositoriesRepository.getRepositories(language, sort, page)
    }
}
