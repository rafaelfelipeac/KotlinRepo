package com.rafaelfelipeac.githubrepositories.features.repositories.data

import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor() : RepositoriesRepository {

    override suspend fun get(welcome: Boolean) {
        TODO("Not yet implemented")
    }

}
