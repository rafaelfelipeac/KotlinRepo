package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.githubrepositories.core.TwoWayMapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import javax.inject.Inject

data class RepositoryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val forks: Int,
    @SerializedName("owner")
    val author: OwnerDto
)

class RepositoryDtoMapper @Inject constructor() : TwoWayMapper<RepositoryDto, Repository> {

    override fun map(param: RepositoryDto): Repository = with(param) {
        Repository(
            name = name,
            stars = stars,
            forks = forks,
            author = author.let(OwnerDtoMapper()::map)
        )
    }

    override fun mapReverse(param: Repository): RepositoryDto = with(param) {
        RepositoryDto(
            name = name,
            stars = stars,
            forks = forks,
            author = author.let(OwnerDtoMapper()::mapReverse)
        )
    }
}
