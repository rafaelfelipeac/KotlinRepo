package com.rafaelfelipeac.githubrepositories.features.repositories.data

import com.rafaelfelipeac.githubrepositories.core.network.Network
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryDtoMapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(
    private val repositoriesApi: RepositoriesApi,
    private val repositoryDtoMapper: RepositoryDtoMapper
) : RepositoriesRepository {

    override suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): ResultWrapper<List<Repository>> {
        return Network.request() {
            repositoriesApi.getAllRepositories(language, sort, page).items
                .map { repositoryDtoMapper.map(it) }
        }
    }
}
