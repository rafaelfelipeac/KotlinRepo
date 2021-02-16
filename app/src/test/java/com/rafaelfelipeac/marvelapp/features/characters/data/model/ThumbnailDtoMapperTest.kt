package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnail
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnailDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class ThumbnailDtoMapperTest {

    private val thumbnailDtoMapper = ThumbnailDtoMapper()

    @Test
    fun `GIVEN ThumbnailDto WHEN map is called THEN Thumbnail is returned`() {
        // given
        val thumbnailDto = createThumbnailDto()

        // when
        val result = thumbnailDtoMapper.map(thumbnailDto)

        // then
        result equalTo createThumbnail()
    }

    @Test
    fun `GIVEN Thumbnail WHEN mapReverse is called THEN ThumbnailDto is returned`() {
        // given
        val thumbnail = createThumbnail()

        // when
        val result = thumbnailDtoMapper.mapReverse(thumbnail)

        // then
        result equalTo createThumbnailDto()
    }

    @Test
    fun `GIVEN a null path parameter WHEN map is called THEN mapped path is empty`() {
        // given 
        val pathParameter = null

        // when
        val result = thumbnailDtoMapper.map(
            createThumbnailDto(
                path = pathParameter
            )
        )

        // then
        result.path equalTo ""
    }

    @Test
    fun `GIVEN a null extension parameter WHEN map is called THEN mapped extension is empty`() {
        // given
        val extensionParameter = null

        // when
        val result = thumbnailDtoMapper.map(
            createThumbnailDto(
                extension = extensionParameter
            )
        )

        // then
        result.extension equalTo ""
    }
}
