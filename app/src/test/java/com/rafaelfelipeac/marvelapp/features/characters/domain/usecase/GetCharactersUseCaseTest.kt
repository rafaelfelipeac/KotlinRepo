package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacters
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockApiKey
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockHash
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockOffset
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockTimestamp
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
class GetCharactersUseCaseTest {

    @Mock
    internal lateinit var mockCharactersRepository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setup() {
        getCharactersUseCase = GetCharactersUseCase(mockCharactersRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getCharactersUseCase is called THEN return a list of characters'`() {
        runBlocking {
            // given
            val characters = createCharacters()
            val success = ResultWrapper.Success(characters)

            given(
                mockCharactersRepository.getCharacters(
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(success)

            // when
            val result = getCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN GenericError result WHEN getCharactersUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(
                mockCharactersRepository.getCharacters(
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(genericError)

            // when
            val result = getCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )

            // then
            result equalTo genericError
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharactersUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(
                mockCharactersRepository.getCharacters(
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(networkError)

            // when
            val result = getCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )

            // then
            result equalTo networkError
        }
    }
}
