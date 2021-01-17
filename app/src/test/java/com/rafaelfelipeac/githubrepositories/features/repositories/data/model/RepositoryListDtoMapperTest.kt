package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryList
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryListDto
import com.rafaelfelipeac.githubrepositories.base.equalTo
import org.junit.Test

class RepositoryListDtoMapperTest {

    private val repositoryListDtoMapper = RepositoryListDtoMapper()

    @Test
    fun `GIVEN RepositoryListDto WHEN map is called THEN RepositoryList is returned`() {
        // given
        val repositoryListDto = createRepositoryListDto()

        // when
        val result = repositoryListDtoMapper.map(repositoryListDto)

        // then
        result equalTo createRepositoryList()
    }

    @Test
    fun `GIVEN RepositoryList WHEN mapReverse is called THEN RepositoryListDto is returned`() {
        // given
        val repositoryList = createRepositoryList()

        // when
        val result = repositoryListDtoMapper.mapReverse(repositoryList)

        // then
        result equalTo createRepositoryListDto()
    }
}
