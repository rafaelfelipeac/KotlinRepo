package com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase

import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository
) {
    suspend operator fun invoke(language: String, sort: String, page: Int): List<Repository> {
        return repositoriesRepository.getRepositories(language, sort, page)
    }
}
