package com.rafaelfelipeac.kotlinrepo.base

import com.rafaelfelipeac.kotlinrepo.features.repositories.data.model.OwnerDto
import com.rafaelfelipeac.kotlinrepo.features.repositories.data.model.RepositoryDto
import com.rafaelfelipeac.kotlinrepo.features.repositories.data.model.RepositoryListDto
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Owner
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.RepositoryList

object DataProviderAndroidTest {

    const val mockLanguage = "language:kotlin"
    const val mockSort = "star"
    const val mockPage = 1

    const val mockOwnerName = "Owner name"
    const val mockOwnerAvatarUrl =
        "https://180dc.org/wp-content/uploads/2017/11/profile-placeholder.png"

    const val mockRepositoryName = "Repository Name"
    const val mockRepositoryStars = 200
    const val mockRepositoryForks = 100

    // region Data
    fun createRepositoryListDto(): RepositoryListDto {
        return RepositoryListDto(createRepositoriesDto())
    }

    fun createRepositoriesDto(): List<RepositoryDto> {
        return listOf(
            createRepositoryDto(),
            createRepositoryDto(),
            createRepositoryDto()
        )
    }

    fun createRepositoryDto(
        name: String? = mockRepositoryName,
        stars: Int? = mockRepositoryStars,
        forks: Int? = mockRepositoryForks,
        login: String? = mockOwnerName,
        avatar_url: String? = mockOwnerAvatarUrl
    ): RepositoryDto {
        return RepositoryDto(
            name,
            stars,
            forks,
            createOwnerDto(login, avatar_url)
        )
    }

    fun createOwnerDto(
        login: String? = mockOwnerName,
        avatar_url: String? = mockOwnerAvatarUrl
    ): OwnerDto {
        return OwnerDto(login, avatar_url)
    }
    // endregion

    // region Domain
    fun createRepositoryList(): RepositoryList {
        return RepositoryList(createRepositories())
    }

    fun createRepositories(): List<Repository> {
        return listOf(
            createRepository(),
            createRepository(),
            createRepository()
        )
    }

    fun createRepository(
        name: String = mockRepositoryName,
        stars: Int = mockRepositoryStars,
        forks: Int = mockRepositoryForks,
        ownerName: String = mockOwnerName,
        ownerAvatarUrl: String = mockOwnerAvatarUrl
    ): Repository {
        return Repository(
            name,
            stars,
            forks,
            createOwner(ownerName, ownerAvatarUrl)
        )
    }

    fun createOwner(
        name: String = mockOwnerName,
        avatarUrl: String = mockOwnerAvatarUrl
    ): Owner {
        return Owner(name, avatarUrl)
    }
    // endregion
}
