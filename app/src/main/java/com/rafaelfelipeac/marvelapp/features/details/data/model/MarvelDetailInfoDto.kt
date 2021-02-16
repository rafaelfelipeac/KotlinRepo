package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.MarvelDetailInfo
import javax.inject.Inject

data class MarvelDetailInfoDto(
    @SerializedName("data")
    val data: DetailInfoListDto
)

class MarvelDetailInfoDtoMapper @Inject constructor() :
    TwoWayMapper<MarvelDetailInfoDto, MarvelDetailInfo> {

    override fun map(param: MarvelDetailInfoDto): MarvelDetailInfo = with(param) {
        MarvelDetailInfo(
            data = data.let(DetailInfoListDtoMapper()::map)
        )
    }

    override fun mapReverse(param: MarvelDetailInfo): MarvelDetailInfoDto = with(param) {
        MarvelDetailInfoDto(
            data = data.let(DetailInfoListDtoMapper()::mapReverse)
        )
    }
}
