package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelCharacterDetail
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelCharacterDetailDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class MarvelCharacterDetailDtoMapperTest {

    private val marvelCharacterDetailDtoMapper = MarvelCharacterDetailDtoMapper()

    @Test
    fun `GIVEN MarvelCharacterDetail WHEN mapReverse is called THEN MarvelCharacterDetailDto is returned`() {
        // given
        val favorite = createMarvelCharacterDetail()

        // when
        val result = marvelCharacterDetailDtoMapper.mapReverse(favorite)

        // then
        result equalTo createMarvelCharacterDetailDto()
    }
}
