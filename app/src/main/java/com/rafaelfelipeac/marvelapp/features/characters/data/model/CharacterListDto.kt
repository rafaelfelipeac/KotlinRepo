package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.CharacterList
import javax.inject.Inject

data class CharacterListDto(
    @SerializedName("items")
    val items: List<CharacterDto>
)

class CharacterListDtoMapper @Inject constructor() : TwoWayMapper<CharacterListDto, CharacterList> {

    override fun map(param: CharacterListDto): CharacterList = with(param) {
        CharacterList(
            items = CharacterDtoMapper().mapList(items)
        )
    }

    override fun mapReverse(param: CharacterList): CharacterListDto = with(param) {
        CharacterListDto(
            items = CharacterDtoMapper().mapListReverse(items)
        )
    }
}
