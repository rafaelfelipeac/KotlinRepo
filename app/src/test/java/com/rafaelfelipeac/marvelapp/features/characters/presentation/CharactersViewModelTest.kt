package com.rafaelfelipeac.marvelapp.features.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelfelipeac.marvelapp.base.CoroutineRule
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacters
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockPage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockSort
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
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
class CharactersViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var mockGetCharactersUseCase = Mockito.mock(GetCharactersUseCase::class.java)

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(
                mockGetCharactersUseCase
        )
    }

    @Test
    fun `GIVEN a Success result WHEN getCharacters is called THEN a list with characters should be returned`() {
        // given
        val characters = createCharacters()
        val success = ResultWrapper.Success(characters)

        given(runBlocking { mockGetCharactersUseCase(mockLanguage, mockSort, mockPage) })
            .willReturn(success)

        // when
        charactersViewModel.getCharacters(mockLanguage, mockSort, mockPage)

        // then
        charactersViewModel.characters.value equalTo characters
    }

    @Test
    fun `GIVEN a GenericError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking { mockGetCharactersUseCase(mockLanguage, mockSort, mockPage) })
                .willReturn(genericError)

        // when
        charactersViewModel.getCharacters(mockLanguage, mockSort, mockPage)

        // then
        runBlocking {
            charactersViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking { mockGetCharactersUseCase(mockLanguage, mockSort, mockPage) })
            .willReturn(networkError)

        // when
        charactersViewModel.getCharacters(mockLanguage, mockSort, mockPage)

        // then
        runBlocking {
            charactersViewModel.error.value equalTo throwable
        }
    }
}
