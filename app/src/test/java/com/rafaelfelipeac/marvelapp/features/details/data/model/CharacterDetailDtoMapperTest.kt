package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetail
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDetailDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class CharacterDetailDtoMapperTest {

    private val characterDetailDtoMapper = CharacterDetailDtoMapper()

    @Test
    fun `GIVEN CharacterDetailDto WHEN map is called THEN CharacterDetail is returned`() {
        // given
        val characterDetailDto = createCharacterDetailDto()

        // when
        val result = characterDetailDtoMapper.map(characterDetailDto)

        // then
        result equalTo createCharacterDetail()
    }

    @Test
    fun `GIVEN CharacterDetail WHEN mapReverse is called THEN CharacterDetailDto is returned`() {
        // given
        val characterDetail = createCharacterDetail()

        // when
        val result = characterDetailDtoMapper.mapReverse(characterDetail)

        // then
        result equalTo createCharacterDetailDto()
    }
}
