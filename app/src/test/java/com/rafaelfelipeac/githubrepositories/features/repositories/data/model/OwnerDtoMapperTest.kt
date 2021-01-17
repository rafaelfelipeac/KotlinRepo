package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createOwner
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createOwnerDto
import com.rafaelfelipeac.githubrepositories.base.equalTo
import org.junit.Test

class OwnerDtoMapperTest {

    private val ownerDtoMapper = OwnerDtoMapper()

    @Test
    fun `WHEN map is called THEN Owner is returned`() {
        // given
        val other = createOwner()

        // when
        val result = ownerDtoMapper.map(createOwnerDto())

        // then
        result equalTo other
    }

    @Test
    fun `WHEN mapReverse is called THEN OwnerDto is returned`() {
        // given
        val other = createOwnerDto()

        // when
        val result = ownerDtoMapper.mapReverse(createOwner())

        // then
        result equalTo other
    }

    @Test
    fun `WHEN login is null THEN mapped name is empty`() {
        // when
        val result = ownerDtoMapper.map(
            createOwnerDto(
                login = null,
            )
        )

        // then
        result.name equalTo ""
    }

    @Test
    fun `WHEN avatar_url is null THEN mapped avatarUrl is empty`() {
        // when
        val result = ownerDtoMapper.map(
            createOwnerDto(
                avatar_url = null
            )
        )

        // then
        result.avatarUrl equalTo ""
    }
}
