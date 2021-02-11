package com.rafaelfelipeac.marvelapp.features.repositories.domain.repository

import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.repositories.domain.model.Repository

interface RepositoriesRepository {

    suspend fun getRepositories(language: String, sort: String, page: Int): ResultWrapper<List<Repository>>
}
