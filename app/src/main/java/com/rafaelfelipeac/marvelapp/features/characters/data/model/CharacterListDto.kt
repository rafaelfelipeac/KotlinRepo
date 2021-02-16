package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.CharacterList
import javax.inject.Inject

data class CharacterListDto(
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val items: List<CharacterDto>
)

class CharacterListDtoMapper @Inject constructor() : TwoWayMapper<CharacterListDto, CharacterList> {

    override fun map(param: CharacterListDto): CharacterList = with(param) {
        CharacterList(
            offset = offset ?: 0,
            limit = limit ?: 0,
            total = total ?: 0,
            count = count ?: 0,
            items = CharacterDtoMapper().mapList(items)
        )
    }

    override fun mapReverse(param: CharacterList): CharacterListDto = with(param) {
        CharacterListDto(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            items = CharacterDtoMapper().mapListReverse(items)
        )
    }
}
