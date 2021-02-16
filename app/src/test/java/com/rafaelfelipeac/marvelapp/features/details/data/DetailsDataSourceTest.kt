package com.rafaelfelipeac.marvelapp.features.details.data

import com.rafaelfelipeac.marvelapp.base.DataProviderTest
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelCharacterDetailDto
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelDetailInfoDto
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockOffset
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.data.model.CharacterDetailDtoMapper
import com.rafaelfelipeac.marvelapp.features.details.data.model.DetailInfoDtoMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsDataSourceTest {

    @Mock
    internal lateinit var detailsApi: DetailsApi

    @Mock
    internal lateinit var characterDetailDtoMapper: CharacterDetailDtoMapper

    @Mock
    internal lateinit var detailInfoDtoMapper: DetailInfoDtoMapper

    private lateinit var detailsDataSource: DetailsDataSource

    @Before
    fun setup() {
        detailsDataSource = DetailsDataSource(detailsApi, characterDetailDtoMapper, detailInfoDtoMapper)
    }

    @Test
    fun `GIVEN Success return from api WHEN getDetails is called THEN Success is returned`() {
        runBlocking {
            // given
            val marvelCharacterDetailDto = createMarvelCharacterDetailDto()
            val characterDetail = characterDetailDtoMapper.map(marvelCharacterDetailDto.data.items.first())
            val success = ResultWrapper.Success(characterDetail)

            given(
                detailsApi.getDetails(
                    mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                )
            ).willReturn(marvelCharacterDetailDto)

            // when
            val result = detailsDataSource.getDetails(
                mockCharacterId,
                DataProviderTest.mockApiKey,
                DataProviderTest.mockHash,
                DataProviderTest.mockTimestamp,
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN Success return from api WHEN getDetailsComics is called THEN Success is returned`() {
        runBlocking {
            // given
            val marvelCharacterDetailDto = createMarvelDetailInfoDto()
            val characterDetail = marvelCharacterDetailDto.data.items.map { detailInfoDtoMapper.map(it)}
            val success = ResultWrapper.Success(characterDetail)

            given(
                detailsApi.getDetailsComics(
                    mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                    mockOffset
                )
            ).willReturn(marvelCharacterDetailDto)

            // when
            val result = detailsDataSource.getDetailsComics(
                mockCharacterId,
                DataProviderTest.mockApiKey,
                DataProviderTest.mockHash,
                DataProviderTest.mockTimestamp,
                mockOffset
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN Success return from api WHEN getDetailsSeries is called THEN Success is returned`() {
        runBlocking {
            runBlocking {
                // given
                val marvelCharacterDetailDto = createMarvelDetailInfoDto()
                val characterDetail = marvelCharacterDetailDto.data.items.map { detailInfoDtoMapper.map(it)}
                val success = ResultWrapper.Success(characterDetail)

                given(
                    detailsApi.getDetailsSeries(
                        mockCharacterId,
                        DataProviderTest.mockApiKey,
                        DataProviderTest.mockHash,
                        DataProviderTest.mockTimestamp,
                        mockOffset
                    )
                ).willReturn(marvelCharacterDetailDto)

                // when
                val result = detailsDataSource.getDetailsSeries(
                    mockCharacterId,
                    DataProviderTest.mockApiKey,
                    DataProviderTest.mockHash,
                    DataProviderTest.mockTimestamp,
                    mockOffset
                )

                // then
                result equalTo success
            }
        }
    }
}
