package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryList
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryListDto
import com.rafaelfelipeac.githubrepositories.base.equalTo
import org.junit.Test

class RepositoryListDtoMapperTest {

    private val repositoryListDtoMapper = RepositoryListDtoMapper()

    @Test
    fun `WHEN map is called THEN RepositoryList is returned`() {
        // given
        val other = createRepositoryList()

        // when
        val result = repositoryListDtoMapper.map(createRepositoryListDto())

        // then
        result equalTo other
    }

    @Test
    fun `WHEN mapReverse is called THEN RepositoryListDto is returned`() {
        // given
        val other = createRepositoryListDto()

        // when
        val result = repositoryListDtoMapper.mapReverse(createRepositoryList())

        // then
        result equalTo other
    }
}
