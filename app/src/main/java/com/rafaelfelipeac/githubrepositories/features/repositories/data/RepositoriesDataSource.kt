package com.rafaelfelipeac.githubrepositories.features.repositories.data

import com.rafaelfelipeac.githubrepositories.core.network.RepositoriesService
import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryListDto
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.core.network.Network
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(
        private val repositoriesService: RepositoriesService
) : RepositoriesRepository {

    override suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<RepositoryListDto> {
        return Network.request() {
            repositoriesService.api.getAllRepositories(language, sort, page)
        }
    }
}
