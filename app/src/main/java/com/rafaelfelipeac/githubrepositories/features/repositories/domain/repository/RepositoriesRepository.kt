package com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository

interface RepositoriesRepository {

    suspend fun get(welcome: Boolean)
}
