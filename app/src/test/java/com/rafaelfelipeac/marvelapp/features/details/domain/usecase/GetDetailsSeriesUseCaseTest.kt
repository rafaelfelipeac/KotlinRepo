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
class GetDetailsSeriesUseCaseTest {

    @Mock
    internal lateinit var detailsRepository: DetailsRepository

    private lateinit var getDetailsSeriesUseCase: GetDetailsSeriesUseCase

    @Before
    fun setup() {
        getDetailsSeriesUseCase = GetDetailsSeriesUseCase(detailsRepository)
    }

    @Test
    fun `GIVEN Success result WHEN getDetailsSeriesUseCase is called THEN return a list of detailInfo'`() {
        runBlocking {
            // given
            val detailInfoList = listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
            val success = ResultWrapper.Success(detailInfoList)

            given(
                detailsRepository.getDetailsSeries(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(success)

            // when
            val result = getDetailsSeriesUseCase(
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
    fun `GIVEN GenericError result WHEN getDetailsSeriesUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(
                detailsRepository.getDetailsSeries(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(genericError)

            // when
            val result = getDetailsSeriesUseCase(
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
    fun `GIVEN a NetworkError result WHEN getDetailsSeriesUseCase is called THEN return a throwable`() {
        runBlocking {
            // given
            val throwable = Exception()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(
                detailsRepository.getDetailsSeries(
                    mockCharacterId,
                    mockApiKey,
                    mockHash,
                    mockTimestamp,
                    mockOffset
                )
            ).willReturn(networkError)

            // when
            val result = getDetailsSeriesUseCase(
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
