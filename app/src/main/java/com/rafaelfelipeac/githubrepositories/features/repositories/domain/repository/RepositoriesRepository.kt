package com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository

import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository

interface RepositoriesRepository {

    suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<List<Repository>>
}
