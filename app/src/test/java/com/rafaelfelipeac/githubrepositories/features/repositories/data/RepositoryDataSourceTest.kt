package com.rafaelfelipeac.githubrepositories.features.repositories.data

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositoryListDto
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockPage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockSort
import com.rafaelfelipeac.githubrepositories.base.equalTo
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.data.model.RepositoryDtoMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class RepositoryDataSourceTest {

    @Mock
    internal lateinit var repositoriesApi: RepositoriesApi

    @Mock
    internal lateinit var repositoryDtoMapper: RepositoryDtoMapper

    private lateinit var repositoriesDataSource: RepositoriesDataSource

    @Before
    fun setup() {
        repositoriesDataSource = RepositoriesDataSource(repositoriesApi, repositoryDtoMapper)
    }

    @Test
    fun `GIVEN api returns successfully WHEN getAllRepositories is called THEN Success is returned`() {
        runBlocking {
            // given
            val repositoryListDto = createRepositoryListDto()
            val repositories = repositoryListDto.items.map { repositoryDtoMapper.map(it) }
            val success = ResultWrapper.Success(repositories)

            given(
                repositoriesApi.getAllRepositories(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willReturn(repositoryListDto)

            // when
            val result = repositoriesDataSource.getRepositories(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN api returns an NetworkError WHEN getAllRepositories is called THEN NetworkError is returned`() {
        runBlocking {
            // given
            val throwable = IOException()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(
                repositoriesApi.getAllRepositories(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willAnswer {
                throw throwable
            }

            // when
            val result = repositoriesDataSource.getRepositories(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo networkError
        }
    }

    @Test
    fun `GIVEN api returns an GenericError WHEN getAllRepositories is called THEN GenericError is returned`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(
                repositoriesApi.getAllRepositories(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willAnswer {
                throw throwable
            }

            // when
            val result = repositoriesDataSource.getRepositories(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo genericError
        }
    }
}