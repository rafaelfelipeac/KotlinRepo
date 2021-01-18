package com.rafaelfelipeac.kotlinrepo.features.repositories.domain.repository

import com.rafaelfelipeac.kotlinrepo.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository

interface RepositoriesRepository {

    suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<List<Repository>>
}
