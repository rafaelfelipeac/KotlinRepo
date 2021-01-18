package com.rafaelfelipeac.kotlinrepo.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.kotlinrepo.core.TwoWayMapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository
import javax.inject.Inject

data class RepositoryDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("stargazers_count")
    val stars: Int?,
    @SerializedName("forks_count")
    val forks: Int?,
    @SerializedName("owner")
    val owner: OwnerDto
)

class RepositoryDtoMapper @Inject constructor() : TwoWayMapper<RepositoryDto, Repository> {

    override fun map(param: RepositoryDto): Repository = with(param) {
        Repository(
            name = name.orEmpty(),
            stars = stars ?: 0,
            forks = forks ?: 0,
            owner = owner.let(OwnerDtoMapper()::map)
        )
    }

    override fun mapReverse(param: Repository): RepositoryDto = with(param) {
        RepositoryDto(
            name = name,
            stars = stars,
            forks = forks,
            owner = owner.let(OwnerDtoMapper()::mapReverse)
        )
    }
}
