package com.rafaelfelipeac.marvelapp.features.details.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailsSeriesUseCaseTest {

    @Mock
    internal lateinit var detailsRepository: DetailsRepository

    private lateinit var getDetailsSeriesUseCase: GetDetailsSeriesUseCase

    @Before
    fun setup() {
        getDetailsSeriesUseCase = GetDetailsSeriesUseCase(detailsRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getCharactersUseCase is called THEN return a list of characters'`() {
        runBlocking {
            // given
            val characters = listOf(DataProviderTest.createDetailInfo(), DataProviderTest.createDetailInfo(), DataProviderTest.createDetailInfo())
            val success = ResultWrapper.Success(characters)

            BDDMockito.given(
                detailsRepository.getDetailsSeries(
                    DataProviderTest.mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                    DataProviderTest.mockOffset
                )
            ).willReturn(success)

            // when
            val result = getDetailsSeriesUseCase(
                DataProviderTest.mockCharacterId,
                DataProviderTest.mockApiKey,
                DataProviderTest.mockTimestamp,
                DataProviderTest.mockHash,
                DataProviderTest.mockOffset
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

            BDDMockito.given(
                detailsRepository.getDetailsSeries(
                    DataProviderTest.mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                    DataProviderTest.mockOffset
                )
            ).willReturn(genericError)

            // when
            val result = getDetailsSeriesUseCase(
                DataProviderTest.mockCharacterId,
                DataProviderTest.mockApiKey,
                DataProviderTest.mockTimestamp,
                DataProviderTest.mockHash,
                DataProviderTest.mockOffset
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

            BDDMockito.given(
                detailsRepository.getDetailsSeries(
                    DataProviderTest.mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                    DataProviderTest.mockOffset
                )
            ).willReturn(networkError)

            // when
            val result = getDetailsSeriesUseCase(
                DataProviderTest.mockCharacterId,
                DataProviderTest.mockApiKey,
                DataProviderTest.mockTimestamp,
                DataProviderTest.mockHash,
                DataProviderTest.mockOffset
            )

            // then
            result equalTo networkError
        }
    }
}

