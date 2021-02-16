package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnailCharacter
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createThumbnailDtoCharacter
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class ThumbnailDtoMapperTest {

    private val thumbnailDtoMapper = ThumbnailDtoMapper()

    @Test
    fun `GIVEN ThumbnailDto WHEN map is called THEN Thumbnail is returned`() {
        // given
        val thumbnailDto = createThumbnailDtoCharacter()

        // when
        val result = thumbnailDtoMapper.map(thumbnailDto)

        // then
        result equalTo createThumbnailCharacter()
    }

    @Test
    fun `GIVEN Thumbnail WHEN mapReverse is called THEN ThumbnailDto is returned`() {
        // given
        val thumbnail = createThumbnailCharacter()

        // when
        val result = thumbnailDtoMapper.mapReverse(thumbnail)

        // then
        result equalTo createThumbnailDtoCharacter()
    }

    @Test
    fun `GIVEN a null path parameter WHEN map is called THEN mapped path is empty`() {
        // given 
        val pathParameter = null

        // when
        val result = thumbnailDtoMapper.map(
            createThumbnailDtoCharacter(
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
            createThumbnailDtoCharacter(
                extension = extensionParameter
            )
        )

        // then
        result.extension equalTo ""
    }
}
