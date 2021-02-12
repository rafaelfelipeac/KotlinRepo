package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterList
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterListDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class CharacterListDtoMapperTest {

    private val characterListDtoMapper = CharacterListDtoMapper()

    @Test
    fun `GIVEN CharacterListDto WHEN map is called THEN CharacterList is returned`() {
        // given
        val characterListDto = createCharacterListDto()

        // when
        val result = characterListDtoMapper.map(characterListDto)

        // then
        result equalTo createCharacterList()
    }

    @Test
    fun `GIVEN CharacterList WHEN mapReverse is called THEN CharacterListDto is returned`() {
        // given
        val characterList = createCharacterList()

        // when
        val result = characterListDtoMapper.mapReverse(characterList)

        // then
        result equalTo createCharacterListDto()
    }
}
