package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import javax.inject.Inject

data class DetailInfoDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto
)

class DetailInfoDtoMapper @Inject constructor() : TwoWayMapper<DetailInfoDto, DetailInfo> {

    override fun map(param: DetailInfoDto): DetailInfo = with(param) {
        DetailInfo(
            id = id ?: 0L,
            title = title.orEmpty(),
            thumbnail = thumbnail.let(ThumbnailDtoMapper()::map)
        )
    }

    override fun mapReverse(param: DetailInfo): DetailInfoDto = with(param) {
        DetailInfoDto(
            id = id,
            title = title,
            thumbnail = thumbnail.let(ThumbnailDtoMapper()::mapReverse)
        )
    }
}
