package com.rafaelfelipeac.githubrepositories.features.base

import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.OwnerDto
import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryDto
import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryListDto
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Owner
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.RepositoryList

object DataProviderTest {

    const val mockLanguage = "language:kotlin"
    const val mockSort = "star"
    const val mockPage = 1

    const val mockAuthorName = "Author name"
    const val mockAuthorImage = "https://180dc.org/wp-content/uploads/2017/11/profile-placeholder.png"

    const val mockRepositoryName = "Repository Name"
    const val mockRepositoryStar = 200
    const val mockRepositoryFork = 100

    fun createRepositoryListDto(): RepositoryListDto {
        return RepositoryListDto(
            listOf(
                createRepositoryDto(),
                createRepositoryDto(),
                createRepositoryDto()
            )
        )
    }

    fun createRepositoryDto(): RepositoryDto {
        return RepositoryDto(
            mockRepositoryName,
            mockRepositoryStar,
            mockRepositoryFork,
            createOwnerDto()
        )
    }

    fun createOwnerDto(): OwnerDto {
        return OwnerDto(mockAuthorName, mockAuthorImage)
    }

    fun createRepositories(): List<Repository> {
        return listOf(createRepository(), createRepository(), createRepository())
    }

    fun createRepositoryList(): RepositoryList {
        return RepositoryList(listOf(createRepository(), createRepository(), createRepository()))
    }

    fun createRepository(): Repository {
        return Repository(
            mockRepositoryName,
            mockRepositoryStar,
            mockRepositoryFork,
            createOwner()
        )
    }

    fun createOwner(): Owner {
        return Owner(mockAuthorName, mockAuthorImage)
    }
}
