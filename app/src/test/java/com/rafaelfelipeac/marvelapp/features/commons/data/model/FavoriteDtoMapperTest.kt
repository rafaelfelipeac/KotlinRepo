package com.rafaelfelipeac.marvelapp.features.commons.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavoriteDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class FavoriteDtoMapperTest {

    private val favoriteDtoMapper = FavoriteDtoMapper()

    @Test
    fun `GIVEN Favorite WHEN mapReverse is called THEN FavoriteDto is returned`() {
        // given
        val favorite = createFavorite()

        // when
        val result = favoriteDtoMapper.mapReverse(favorite)

        // then
        result equalTo createFavoriteDto()
    }
}
