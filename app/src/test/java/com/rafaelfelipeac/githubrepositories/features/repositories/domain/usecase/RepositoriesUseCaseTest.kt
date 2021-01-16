package com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase

import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositories
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockPage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockSort
import com.rafaelfelipeac.githubrepositories.base.equalTo
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoriesUseCaseTest {

    @Mock
    internal lateinit var mockRepositoriesRepository: RepositoriesRepository

    private lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Before
    fun setup() {
        getRepositoriesUseCase = GetRepositoriesUseCase(mockRepositoriesRepository)
    }

    @Test
    fun `GIVEN a call to getRepositoriesUseCase WHEN it succeeds THEN the repository return a list of repositories`() {
        runBlocking {
            // given
            val repositories = createRepositories()
            val success = ResultWrapper.Success(repositories)

            given(mockRepositoriesRepository.getRepositories(mockLanguage, mockSort, mockPage))
                .willReturn(success)

            // when
            val result = getRepositoriesUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN a call to getRepositoriesUseCase WHEN it fails for a genericError THEN the repository return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(mockRepositoriesRepository.getRepositories(mockLanguage, mockSort, mockPage))
                .willReturn(genericError)

            // when
            val result = getRepositoriesUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo genericError
        }
    }

    @Test
    fun `GIVEN a call to getRepositoriesUseCase WHEN it fails for a networkError THEN the repository return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(mockRepositoriesRepository.getRepositories(mockLanguage, mockSort, mockPage))
                .willReturn(networkError)

            // when
            val result = getRepositoriesUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo networkError
        }
    }
}
