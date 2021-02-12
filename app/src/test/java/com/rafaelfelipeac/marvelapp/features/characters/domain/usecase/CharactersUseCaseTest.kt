package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacters
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockPage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockSort
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.repository.CharactersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersUseCaseTest {

    @Mock
    internal lateinit var mockCharactersRepository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCase(mockCharactersRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getCharactersUseCase is called THEN character return a list of characters'`() {
        runBlocking {
            // given
            val characters = createCharacters()
            val success = ResultWrapper.Success(characters)

            given(mockCharactersRepository.getCharacters(mockLanguage, mockSort, mockPage))
                .willReturn(success)

            // when
            val result = getCharactersUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN GenericError result WHEN getCharactersUseCase is called THEN the character return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(mockCharactersRepository.getCharacters(mockLanguage, mockSort, mockPage))
                .willReturn(genericError)

            // when
            val result = getCharactersUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo genericError
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharactersUseCase is called THEN the character return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(mockCharactersRepository.getCharacters(mockLanguage, mockSort, mockPage))
                .willReturn(networkError)

            // when
            val result = getCharactersUseCase(mockLanguage, mockSort, mockPage)

            // then
            result equalTo networkError
        }
    }
}
