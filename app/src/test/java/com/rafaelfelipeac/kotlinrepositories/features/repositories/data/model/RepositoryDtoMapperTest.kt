package com.rafaelfelipeac.kotlinrepositories.features.repositories.data.model

import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.createRepository
import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.createRepositoryDto
import com.rafaelfelipeac.kotlinrepositories.base.equalTo
import org.junit.Test

class RepositoryDtoMapperTest {

    private val repositoryDtoMapper = RepositoryDtoMapper()

    @Test
    fun `GIVEN RepositoryDto WHEN map is called THEN Repository is returned`() {
        // given
        val repositoryDto = createRepositoryDto()

        // when
        val result = repositoryDtoMapper.map(repositoryDto)

        // then
        result equalTo createRepository()
    }

    @Test
    fun `GIVEN Repository WHEN mapReverse is called THEN RepositoryDto is returned`() {
        // given
        val repository = createRepository()

        // when
        val result = repositoryDtoMapper.mapReverse(repository)

        // then
        result equalTo createRepositoryDto()
    }

    @Test
    fun `GIVEN a null name parameter WHEN map is called THEN mapped name is empty`() {
        // given
        val nameParameter = null

        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                name = nameParameter
            )
        )

        // then
        result.name equalTo ""
    }

    @Test
    fun `GIVEN a null stars parameter WHEN map is called THEN mapped stars is empty`() {
        // given
        val starsParameter = null

        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                stars = starsParameter
            )
        )

        // then
        result.stars equalTo 0
    }

    @Test
    fun `GIVEN a null forks parameter WHEN map is called THEN mapped forks is empty`() {
        // given
        val forksParameter = null

        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                forks = forksParameter
            )
        )

        // then
        result.forks equalTo 0
    }

    @Test
    fun `GIVEN a null login parameter WHEN map is called THEN mapped ownerName is empty`() {
        // given
        val loginParameter = null

        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                login = loginParameter
            )
        )

        // then
        result.owner.name equalTo ""
    }

    @Test
    fun `GIVEN a null avatarUrl parameter WHEN map is called THEN mapped ownerAvatarUrl is empty`() {
        // given
        val avatarUrlParameter = null

        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                avatar_url = avatarUrlParameter
            )
        )

        // then
        result.owner.avatarUrl equalTo ""
    }
}
