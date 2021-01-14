package com.rafaelfelipeac.githubrepositories.features.repositories.data

import com.rafaelfelipeac.githubrepositories.features.repositories.RepositoriesService
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoriesDataSource @Inject constructor(
    private val repositoriesService: RepositoriesService
) : RepositoriesRepository {

    override suspend fun getRepositories(
        language: String,
        sort: String,
        page: Int
    ): List<Repository> {
        return withContext(Dispatchers.IO) {
            val list: MutableList<Repository> = mutableListOf()

            val res = repositoriesService.api.getAllRepositories(language, sort, page)

            if (res.isSuccessful) {
                val items = res.body()?.items

                items?.forEach {
                    list.add(
                        Repository(
                            it.name,
                            it.stars,
                            it.fork,
                            it.author.authorAvatar,
                            it.author.authorName
                        )
                    )
                }
            }

            list
        }
    }
}
