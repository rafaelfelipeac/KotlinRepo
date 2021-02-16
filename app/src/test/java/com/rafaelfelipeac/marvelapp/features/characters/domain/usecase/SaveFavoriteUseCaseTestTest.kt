package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockFavoriteId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockFavoriteName
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockFavoriteUrl
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.FavoriteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveFavoriteUseCaseTestTest {

    @Mock
    internal lateinit var mockFavoriteRepository: FavoriteRepository

    private lateinit var saveFavoriteUseCase: SaveFavoriteUseCase

    @Before
    fun setup() {
        saveFavoriteUseCase = SaveFavoriteUseCase(mockFavoriteRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN saveFavoriteUseCase is called THEN return a favoriteId'`() {
        runBlocking {
            // given
            val favorite = createFavorite(mockFavoriteId, mockFavoriteName, mockFavoriteUrl)

            given(mockFavoriteRepository.save(favorite))
                .willReturn(mockFavoriteId)

            // when
            val result = saveFavoriteUseCase(favorite)

            // then
            result equalTo mockFavoriteId
        }
    }
}
