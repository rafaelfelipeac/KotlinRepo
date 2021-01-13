package com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase

import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repositoriesRepository: RepositoriesRepository
) {
    suspend operator fun invoke(welcome: Boolean) {
        return repositoriesRepository.get(welcome)
    }
}
