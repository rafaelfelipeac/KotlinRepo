package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.githubrepositories.core.Mapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import javax.inject.Inject

data class RepositoryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val fork: Int,
    @SerializedName("owner")
    val author: OwnerDto
)

class RepositoryDtoMapper @Inject constructor() : Mapper<RepositoryDto, Repository> {

    override fun map(param: RepositoryDto): Repository = with(param) {
        Repository(
            name = name,
            stars = stars,
            forks = fork,
            author = author.let(OwnerDtoMapper()::map)
        )
    }
}
