package com.rafaelfelipeac.marvelapp.features.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelfelipeac.marvelapp.base.CoroutineRule
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetail
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfo
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockApiKey
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterName
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterUrl
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockHash
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockOffset
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockTimestamp
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsComicsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var mockGetDetailsUseCase = mock(GetDetailsUseCase::class.java)
    private var mockGetDetailsComicsUseCase = mock(GetDetailsComicsUseCase::class.java)
    private var mockGetDetailsSeriesUseCase = mock(GetDetailsSeriesUseCase::class.java)
    private var mockSaveFavoriteUseCase =
        mock(com.rafaelfelipeac.marvelapp.features.details.domain.usecase.SaveFavoriteUseCase::class.java)

    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setup() {
        detailsViewModel = DetailsViewModel(
            mockGetDetailsUseCase,
            mockGetDetailsComicsUseCase,
            mockGetDetailsSeriesUseCase,
            mockSaveFavoriteUseCase
        )
    }

    @Test
    fun `GIVEN a Success result WHEN getCharacters is called THEN a list with characters should be returned`() {
        // given
        val characterDetail = createCharacterDetail()
        val success = ResultWrapper.Success(characterDetail)

        given(runBlocking {
            mockGetDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
            )
        }).willReturn(success)

        // when
        detailsViewModel.getDetails(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash
        )

        // then
        detailsViewModel.details.value equalTo characterDetail
    }

    @Test
    fun `GIVEN a GenericError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking {
            mockGetDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash
            )
        }).willReturn(genericError)

        // when
        detailsViewModel.getDetails(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash
        )

        // then
        runBlocking {
            detailsViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking {
            mockGetDetailsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
            )
        }).willReturn(networkError)

        // when
        detailsViewModel.getDetails(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash
        )

        // then
        runBlocking {
            detailsViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a Success result WHEN getCharacters is called THEN a list with characters should be returned2`() {
        // given
        val characterDetail = listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
        val success = ResultWrapper.Success(characterDetail)

        given(runBlocking {
            mockGetDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(success)

        // when
        detailsViewModel.getDetailsComics(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        detailsViewModel.comics.value equalTo characterDetail
    }

    @Test
    fun `GIVEN a GenericError result WHEN getCharacters is called THEN a throwable should be returned2`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking {
            mockGetDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(genericError)

        // when
        detailsViewModel.getDetailsComics(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        runBlocking {
            detailsViewModel.errorComics.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharacters is called THEN a throwable should be returned2`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking {
            mockGetDetailsComicsUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(networkError)

        // when
        detailsViewModel.getDetailsComics(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        runBlocking {
            detailsViewModel.errorComics.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a Success result WHEN getCharacters is called THEN a list with characters should be returned3`() {
        // given
        val characterDetail = listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
        val success = ResultWrapper.Success(characterDetail)

        given(runBlocking {
            mockGetDetailsSeriesUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(success)

        // when
        detailsViewModel.getDetailsSeries(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        detailsViewModel.series.value equalTo characterDetail
    }

    @Test
    fun `GIVEN a GenericError result WHEN getCharacters is called THEN a throwable should be returned3`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking {
            mockGetDetailsSeriesUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(genericError)

        // when
        detailsViewModel.getDetailsSeries(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        runBlocking {
            detailsViewModel.errorSeries.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharacters is called THEN a throwable should be returned3`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking {
            mockGetDetailsSeriesUseCase(
                mockCharacterId,
                mockApiKey,
                mockTimestamp,
                mockHash,
                mockOffset
            )
        }).willReturn(networkError)

        // when
        detailsViewModel.getDetailsSeries(
            mockCharacterId,
            mockApiKey,
            mockTimestamp,
            mockHash,
            mockOffset
        )

        // then
        runBlocking {
            detailsViewModel.errorSeries.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN favoriteCharacter is called THEN a characterId must be returned`() {
        // given
        val favorite = createFavorite(
            mockCharacterId,
            mockCharacterName,
            mockCharacterUrl
        )

        given(runBlocking {
            mockSaveFavoriteUseCase(
                favorite
            )
        }).willReturn(mockCharacterId)

        // when
        detailsViewModel.favoriteCharacter(favorite)

        // then
        runBlocking {
            detailsViewModel.savedFavorite.value equalTo mockCharacterId
        }
    }
}
