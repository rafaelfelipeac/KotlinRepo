package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfo
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockApiKey
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockHash
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockOffset
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockTimestamp
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailsComicsUseCaseTest {

    @Mock
    internal lateinit var detailsRepository: DetailsRepository

    private lateinit var getDetailsComicsUseCase: GetDetailsComicsUseCase

    @Before
    fun setup() {
        getDetailsComicsUseCase = GetDetailsComicsUseCase(detailsRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getCharactersUseCase is called THEN return a list of characters'`() {
        runBlocking {
            // given
            val characters = listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
            val success = ResultWrapper.Success(characters)

            given(
                detailsRepository.getDetailsComics(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(success)

            // when
            val result = getDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
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
                detailsRepository.getDetailsComics(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(genericError)

            // when
            val result = getDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
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
                detailsRepository.getDetailsComics(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(networkError)

            // when
            val result = getDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )

            // then
            result equalTo networkError
        }
    }
}
