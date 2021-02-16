package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelCharacterList
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelCharacterListDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class MarvelCharacterListCharacterListDtoMapperTest {

    private val marvelCharacterListDtoMapper = MarvelCharacterListDtoMapper()

    @Test
    fun `GIVEN MarvelCharacterListDto WHEN map is called THEN MarvelCharacterList is returned`() {
        // given
        val marvelCharacterListDto = createMarvelCharacterListDto()

        // when
        val result = marvelCharacterListDtoMapper.map(marvelCharacterListDto)

        // then
        result equalTo createMarvelCharacterList()
    }

    @Test
    fun `GIVEN MarvelCharacterList WHEN mapReverse is called THEN MarvelCharacterListDto is returned`() {
        // given
        val marvelCharacterList = createMarvelCharacterList()

        // when
        val result = marvelCharacterListDtoMapper.mapReverse(marvelCharacterList)

        // then
        result equalTo createMarvelCharacterListDto()
    }
}
