package com.rafaelfelipeac.marvelapp.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.repositories.domain.model.Owner
import javax.inject.Inject

data class OwnerDto(
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
)

class OwnerDtoMapper @Inject constructor() : TwoWayMapper<OwnerDto, Owner> {

    override fun map(param: OwnerDto): Owner = with(param) {
        Owner(
            name = login.orEmpty(),
            avatarUrl = avatarUrl.orEmpty()
        )
    }

    override fun mapReverse(param: Owner): OwnerDto = with(param) {
        OwnerDto(
            login = name,
            avatarUrl = avatarUrl
        )
    }
}
