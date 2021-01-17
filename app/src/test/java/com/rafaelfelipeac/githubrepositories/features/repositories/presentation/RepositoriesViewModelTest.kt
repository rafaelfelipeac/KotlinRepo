package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelfelipeac.githubrepositories.base.CoroutineRule
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.createRepositories
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockPage
import com.rafaelfelipeac.githubrepositories.base.DataProviderTest.mockSort
import com.rafaelfelipeac.githubrepositories.base.equalTo
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoriesViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var mockGetRepositoriesUseCase = Mockito.mock(GetRepositoriesUseCase::class.java)

    private lateinit var repositoriesViewModel: RepositoriesViewModel

    @Before
    fun setup() {
        repositoriesViewModel = RepositoriesViewModel(
                mockGetRepositoriesUseCase
        )
    }

    @Test
    fun `GIVEN a call to getRepositories WHEN it succeeds THEN a list with repositories should be returned`() {
        // given
        val repositories = createRepositories()
        val success = ResultWrapper.Success(repositories)

        given(runBlocking { mockGetRepositoriesUseCase(mockLanguage, mockSort, mockPage) })
            .willReturn(success)

        // when
        repositoriesViewModel.getRepositories(mockLanguage, mockSort, mockPage)

        // then
        repositoriesViewModel.repositories.value equalTo repositories
    }

    @Test
    fun `GIVEN a call to getRepositories WHEN it fails for genericError THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking { mockGetRepositoriesUseCase(mockLanguage, mockSort, mockPage) })
                .willReturn(genericError)

        // when
        repositoriesViewModel.getRepositories(mockLanguage, mockSort, mockPage)

        // then
        runBlocking {
            repositoriesViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a call to getRepositories WHEN it fails for networkError THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking { mockGetRepositoriesUseCase(mockLanguage, mockSort, mockPage) })
            .willReturn(networkError)

        // when
        repositoriesViewModel.getRepositories(mockLanguage, mockSort, mockPage)

        // then
        runBlocking {
            repositoriesViewModel.error.value equalTo throwable
        }
    }
}
