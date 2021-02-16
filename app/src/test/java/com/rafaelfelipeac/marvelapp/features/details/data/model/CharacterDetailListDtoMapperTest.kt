package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetailList
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetailListDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class CharacterDetailListDtoMapperTest {
    private val characterDetailListDtoMapper = CharacterDetailListDtoMapper()

    @Test
    fun `GIVEN CharacterDetailListDto WHEN map is called THEN CharacterDetailDto is returned`() {
        // given
        val characterDetailListDto = createCharacterDetailListDto()

        // when
        val result = characterDetailListDtoMapper.map(characterDetailListDto)

        // then
        result equalTo createCharacterDetailList()
    }

    @Test
    fun `GIVEN CharacterDetailDto WHEN mapReverse is called THEN CharacterDetailListDto is returned`() {
        // given
        val characterDetailList = createCharacterDetailList()

        // when
        val result = characterDetailListDtoMapper.mapReverse(characterDetailList)

        // then
        result equalTo createCharacterDetailListDto()
    }
}
