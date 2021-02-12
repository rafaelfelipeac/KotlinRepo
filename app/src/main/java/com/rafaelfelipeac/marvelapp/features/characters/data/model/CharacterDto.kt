package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import javax.inject.Inject

data class CharacterDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("stargazers_count")
    val stars: Int?,
    @SerializedName("forks_count")
    val forks: Int?,
    @SerializedName("owner")
    val owner: OwnerDto
)

class CharacterDtoMapper @Inject constructor() : TwoWayMapper<CharacterDto, Character> {

    override fun map(param: CharacterDto): Character = with(param) {
        Character(
            name = name.orEmpty(),
            stars = stars ?: 0,
            forks = forks ?: 0,
            owner = owner.let(OwnerDtoMapper()::map)
        )
    }

    override fun mapReverse(param: Character): CharacterDto = with(param) {
        CharacterDto(
            name = name,
            stars = stars,
            forks = forks,
            owner = owner.let(OwnerDtoMapper()::mapReverse)
        )
    }
}
