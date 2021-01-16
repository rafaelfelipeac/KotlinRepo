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
}
