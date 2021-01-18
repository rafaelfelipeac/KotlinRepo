package com.rafaelfelipeac.kotlinrepo.features.repositories.data

import com.rafaelfelipeac.kotlinrepo.core.network.Network
import com.rafaelfelipeac.kotlinrepo.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.data.model.RepositoryDtoMapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.repository.RepositoriesRepository
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
