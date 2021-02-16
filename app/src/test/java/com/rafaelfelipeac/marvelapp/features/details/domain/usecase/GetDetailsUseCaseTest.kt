package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetail
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockApiKey
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockHash
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
class GetDetailsUseCaseTest {

    @Mock
    internal lateinit var detailsRepository: DetailsRepository

    private lateinit var getDetailsUseCase: GetDetailsUseCase

    @Before
    fun setup() {
        getDetailsUseCase = GetDetailsUseCase(detailsRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getDetailsUseCase is called THEN return a characterDetail'`() {
        runBlocking {
            // given
            val characterDetail = createCharacterDetail()
            val success = ResultWrapper.Success(characterDetail)

            given(
                detailsRepository.getDetails(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                )
            ).willReturn(success)

            // when
            val result = getDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN GenericError result WHEN getDetailsUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(
                detailsRepository.getDetails(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                )
            ).willReturn(genericError)

            // when
            val result = getDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
            )

            // then
            result equalTo genericError
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getDetailsUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(
                detailsRepository.getDetails(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                )
            ).willReturn(networkError)

            // when
            val result = getDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
            )

            // then
            result equalTo networkError
        }
    }

}