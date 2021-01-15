package com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository

import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryListDto
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper

interface RepositoriesRepository {

    suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<RepositoryListDto>
}
