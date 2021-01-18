package com.rafaelfelipeac.kotlinrepositories.features.repositories.data

import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.createRepositoryListDto
import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.mockPage
import com.rafaelfelipeac.kotlinrepositories.base.DataProviderTest.mockSort
import com.rafaelfelipeac.kotlinrepositories.base.equalTo
import com.rafaelfelipeac.kotlinrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepositories.features.repositories.data.model.RepositoryDtoMapper
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
    fun `GIVEN Success return from api WHEN getAllRepositories is called THEN Success is returned`() {
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
    fun `GIVEN NetworkError return from api WHEN getAllRepositories is called THEN NetworkError is returned`() {
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
    fun `GIVEN GeneticError return from api WHEN getAllRepositories is called THEN GenericError is returned`() {
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
