package com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.repository

import com.rafaelfelipeac.kotlinrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.model.Repository

interface RepositoriesRepository {

    suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<List<Repository>>
}
