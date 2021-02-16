package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnailDetails
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnailDtoDetails
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class ThumbnailDtoMapperTest {
    private val thumbnailDtoMapper = ThumbnailDtoMapper()

    @Test
    fun `GIVEN ThumbnailDto WHEN map is called THEN Thumbnail is returned`() {
        // given
        val thumbnailDto = createThumbnailDtoDetails()

        // when
        val result = thumbnailDtoMapper.map(thumbnailDto)

        // then
        result equalTo createThumbnailDetails()
    }

    @Test
    fun `GIVEN Thumbnail WHEN mapReverse is called THEN ThumbnailDto is returned`() {
        // given
        val favorite = createThumbnailDetails()

        // when
        val result = thumbnailDtoMapper.mapReverse(favorite)

        // then
        result equalTo createThumbnailDtoDetails()
    }
}
