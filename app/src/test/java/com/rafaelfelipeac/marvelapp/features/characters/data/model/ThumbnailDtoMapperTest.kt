package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createOwner
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createOwnerDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class ThumbnailDtoMapperTest {

    private val ownerDtoMapper = ThumbnailDtoMapper()

    @Test
    fun `GIVEN OwnerDto WHEN map is called THEN Owner is returned`() {
        // given
        val ownerDto = createOwnerDto()

        // when
        val result = ownerDtoMapper.map(ownerDto)

        // then
        result equalTo createOwner()
    }

    @Test
    fun `GIVEN Owner WHEN mapReverse is called THEN OwnerDto is returned`() {
        // given
        val owner = createOwner()

        // when
        val result = ownerDtoMapper.mapReverse(owner)

        // then
        result equalTo createOwnerDto()
    }

    @Test
    fun `GIVEN a null login parameter WHEN map is called THEN mapped name is empty`() {
        // given 
        val loginParameter = null

        // when
        val result = ownerDtoMapper.map(
            createOwnerDto(
                login = loginParameter
            )
        )

        // then
        result.path equalTo ""
    }

    @Test
    fun `GIVEN a null avatar_url parameter WHEN map is called THEN mapped avatarUrl is empty`() {
        // given
        val avatarUrlParameter = null

        // when
        val result = ownerDtoMapper.map(
            createOwnerDto(
                avatar_url = avatarUrlParameter
            )
        )

        // then
        result.extension equalTo ""
    }
}