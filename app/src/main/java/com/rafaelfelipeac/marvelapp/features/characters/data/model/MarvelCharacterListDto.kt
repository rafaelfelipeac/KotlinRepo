package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.MarvelCharacterList
import javax.inject.Inject

data class MarvelCharacterListDto(
    @SerializedName("data")
    val data: CharacterListDto
)

class MarvelCharacterListDtoMapper @Inject constructor() :
    TwoWayMapper<MarvelCharacterListDto, MarvelCharacterList> {

    override fun map(param: MarvelCharacterListDto): MarvelCharacterList = with(param) {
        MarvelCharacterList(
            data = data.let(CharacterListDtoMapper()::map)
        )
    }

    override fun mapReverse(param: MarvelCharacterList): MarvelCharacterListDto = with(param) {
        MarvelCharacterListDto(
            data = data.let(CharacterListDtoMapper()::mapReverse)
        )
    }
}
