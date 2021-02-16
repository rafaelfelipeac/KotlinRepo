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
class DeleteFavoriteUseCaseTest {

    @Mock
    internal lateinit var favoriteRepository: FavoriteRepository

    private lateinit var deleteFavoriteUseCase: DeleteFavoriteUseCase

    @Before
    fun setup() {
        deleteFavoriteUseCase = DeleteFavoriteUseCase(favoriteRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN deleteFavoriteUseCase is called THEN return a Unit'`() {
        runBlocking {
            // given
            val favorite = createFavorite()

            given(favoriteRepository.delete(favorite))
                .willReturn(Unit)

            // when
            val result = deleteFavoriteUseCase(favorite)

            // then
            result equalTo Unit
        }
    }
}
