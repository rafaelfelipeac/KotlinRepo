package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelfelipeac.marvelapp.base.CoroutineRule
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoritesUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.SaveListModeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModel {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var getFavoritesUseCase = Mockito.mock(GetFavoritesUseCase::class.java)
    private var deleteFavoriteUseCase = Mockito.mock(DeleteFavoriteUseCase::class.java)
    private var saveListModeUseCase = Mockito.mock(SaveListModeUseCase::class.java)
    private var getListModeUseCase = Mockito.mock(GetListModeUseCase::class.java)

    private lateinit var favoritesViewModel: FavoritesViewModel

    @Before
    fun setup() {
        favoritesViewModel = FavoritesViewModel(
            getFavoritesUseCase,
            deleteFavoriteUseCase,
            saveListModeUseCase,
            getListModeUseCase
        )
    }

    @Test
    fun `GIVEN a Success result WHEN getFavorites is called THEN a list with favorites should be returned`() {
        // given
        val favorites = listOf(createFavorite(), createFavorite(), createFavorite())

        given(runBlocking { getFavoritesUseCase() })
            .willReturn(favorites)

        // when
        favoritesViewModel.getFavorites()

        // then
        favoritesViewModel.favorites.value equalTo favorites
    }

    @Test
    fun `GIVEN a Success result WHEN deleteFavorite is called THEN a Unit should be returned`() {
        // given
        val favorite = createFavorite()

        given(runBlocking { deleteFavoriteUseCase(favorite) })
            .willReturn(Unit)

        // when
        favoritesViewModel.deleteFavorite(favorite)

        // then
        favoritesViewModel.deleted.value equalTo Unit
    }

    @Test
    fun `GIVEN a Success result WHEN saveListMode is called THEN a Unit should be returned`() {
        // given
        val listMode = true

        given(runBlocking { saveListModeUseCase(listMode) })
            .willReturn(Unit)

        // when
        favoritesViewModel.saveListMode(listMode)

        // then
        favoritesViewModel.savedListMode.value equalTo Unit
    }

    @Test
    fun `GIVEN a Success result WHEN getListMode is called THEN the listMode as true should be returned4`() {
        // given
        val listMode = true

        given(runBlocking { getListModeUseCase() }).willReturn(listMode)

        // when
        favoritesViewModel.getListMode()

        // then
        favoritesViewModel.listMode.value equalTo listMode
    }
}
