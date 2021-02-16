package com.rafaelfelipeac.marvelapp.features.commons.data

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockFavoriteId
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.commons.data.model.FavoriteDtoMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteDataSourceTest {

    @Mock
    internal lateinit var favoriteDao: FavoriteDao

    @Mock
    internal lateinit var favoriteDtoMapper: FavoriteDtoMapper

    private lateinit var favoriteDataSource: FavoriteDataSource

    @Before
    fun setup() {
        favoriteDataSource = FavoriteDataSource(favoriteDao, favoriteDtoMapper)
    }

    @Test
    fun `GIVEN a favoriteId WHEN getFavorite is called THEN return a favorite with the specific favoriteId`() {
        runBlocking {
            // given
            val favorite = createFavorite(mockFavoriteId).let { favoriteDtoMapper.mapReverse(it) }
            given(favoriteDao.get(mockFavoriteId))
                .willReturn(favorite)

            // when
            val result = favoriteDataSource.getFavorite(mockFavoriteId)

            // then
            result equalTo favorite
        }
    }

    @Test
    fun `GIVEN a list of favorites WHEN getFavorites is called THEN return a list with the same favorites`() {
        runBlocking {
            // given
            val favorites = listOf(
                createFavorite(),
                createFavorite(),
                createFavorite()
            ).let { favoriteDtoMapper.mapListReverse(it) }
            given(favoriteDao.getAll())
                .willReturn(favorites)

            // when
            val result = favoriteDataSource.getFavorites()

            // then
            result equalTo favorites
        }
    }

    @Test
    fun `GIVEN a favorite WHEN save is called THEN return the favoriteId as a confirmation`() {
        runBlocking {
            // given
            val favorite = createFavorite(mockFavoriteId)
            given(favoriteDao.save(favorite.let { favoriteDtoMapper.mapReverse(it) }))
                .willReturn(mockFavoriteId)

            // when
            val result = favoriteDataSource.save(favorite)

            // then
            result equalTo mockFavoriteId
        }
    }

    @Test
    fun `GIVEN a favorite with a specific favoriteId WHEN delete is called THEN return just a Unit value`() {
        runBlocking {
            // given
            val favorite = createFavorite(mockFavoriteId)
            val favoriteReverse = favorite.let { favoriteDtoMapper.mapReverse(it) }
            doNothing().`when`(favoriteDao).delete(favoriteReverse)

            // when
            val result = favoriteDataSource.delete(favorite)

            // then
            result equalTo Unit
        }
    }
}
