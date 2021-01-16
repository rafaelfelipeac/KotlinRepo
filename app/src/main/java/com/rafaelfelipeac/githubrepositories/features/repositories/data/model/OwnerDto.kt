package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.githubrepositories.core.TwoWayMapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Owner
import javax.inject.Inject

data class OwnerDto(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
)

class OwnerDtoMapper @Inject constructor() : TwoWayMapper<OwnerDto, Owner> {

    override fun map(param: OwnerDto): Owner = with(param) {
        Owner(
            name = login,
            authorImage = avatar_url
        )
    }

    override fun mapReverse(param: Owner): OwnerDto = with(param) {
        OwnerDto(
            login = name,
            avatar_url = authorImage
        )
    }
}
