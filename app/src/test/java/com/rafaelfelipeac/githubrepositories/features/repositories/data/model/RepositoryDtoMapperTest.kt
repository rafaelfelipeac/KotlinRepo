package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepository
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryDto
import com.rafaelfelipeac.githubrepositories.base.equalTo
import org.junit.Test

class RepositoryDtoMapperTest {

    private val repositoryDtoMapper = RepositoryDtoMapper()

    @Test
    fun `WHEN map is called THEN Repository is returned`() {
        // given
        val other = createRepository()

        // when
        val result = repositoryDtoMapper.map(createRepositoryDto())

        // then
        result equalTo other
    }

    @Test
    fun `WHEN mapReverse is called THEN RepositoryDto is returned`() {
        // given
        val other = createRepositoryDto()

        // when
        val result = repositoryDtoMapper.mapReverse(createRepository())

        // then
        result equalTo other
    }

    @Test
    fun `WHEN name is null THEN mapped name is empty`() {
        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                name = null,
            )
        )

        // then
        result.name equalTo ""
    }

    @Test
    fun `WHEN stars is null THEN mapped stars is empty`() {
        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                stars = null,
            )
        )

        // then
        result.stars equalTo 0
    }

    @Test
    fun `WHEN forks is null THEN mapped forks is empty`() {
        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                forks = null
            )
        )

        // then
        result.forks equalTo 0
    }

    @Test
    fun `WHEN login is null THEN mapped ownerName is empty`() {
        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                login = null
            )
        )

        // then
        result.owner.name equalTo ""
    }

    @Test
    fun `WHEN avatar_url is null THEN mapped ownerAvatarUrl is empty`() {
        // when
        val result = repositoryDtoMapper.map(
            createRepositoryDto(
                avatar_url = null,
            )
        )

        // then
        result.owner.avatarUrl equalTo ""
    }
}
