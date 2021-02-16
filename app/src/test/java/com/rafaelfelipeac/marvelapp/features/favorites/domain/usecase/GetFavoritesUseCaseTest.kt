package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
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
class GetFavoritesUseCaseTest {

    @Mock
    internal lateinit var favoriteRepository: FavoriteRepository

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Before
    fun setup() {
        getFavoritesUseCase = GetFavoritesUseCase(favoriteRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN getFavoritesUseCase is called THEN return a list of favorites'`() {
        runBlocking {
            // given
            val favorites = listOf(createFavorite(), createFavorite(), createFavorite())

            given(favoriteRepository.getFavorites())
                .willReturn(favorites)

            // when
            val result = getFavoritesUseCase()

            // then
            result equalTo favorites
        }
    }
}
