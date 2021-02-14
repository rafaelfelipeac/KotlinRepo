package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Marvel
import javax.inject.Inject

data class MarvelDto(
    @SerializedName("data")
    val data: CharacterListDto,
)

class MarvelDtoMapper @Inject constructor() : TwoWayMapper<MarvelDto, Marvel> {

    override fun map(param: MarvelDto): Marvel = with(param) {
        Marvel(
            data = data.let(CharacterListDtoMapper()::map)
        )
    }

    override fun mapReverse(param: Marvel): MarvelDto = with(param) {
        MarvelDto(
            data = data.let(CharacterListDtoMapper()::mapReverse)
        )
    }
}
