package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.marvelapp.core.TwoWayMapper
import com.rafaelfelipeac.marvelapp.features.details.domain.model.Thumbnail
import javax.inject.Inject

data class ThumbnailDto(
    @SerializedName("path")
    val path: String?,
    @SerializedName("extension")
    val extension: String?
)

class ThumbnailDtoMapper @Inject constructor() : TwoWayMapper<ThumbnailDto, Thumbnail> {

    override fun map(param: ThumbnailDto): Thumbnail = with(param) {
        Thumbnail(
            path = path.orEmpty(),
            extension = extension.orEmpty()
        )
    }

    override fun mapReverse(param: Thumbnail): ThumbnailDto = with(param) {
        ThumbnailDto(
            path = path,
            extension = extension
        )
    }
}
