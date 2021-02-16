package com.rafaelfelipeac.marvelapp.features.characters.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacter
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class CharacterDtoMapperTest {

    private val characterDtoMapper = CharacterDtoMapper()

    @Test
    fun `GIVEN CharacterDto WHEN map is called THEN Character is returned`() {
        // given
        val characterDto = createCharacterDto()

        // when
        val result = characterDtoMapper.map(characterDto)

        // then
        result equalTo createCharacter()
    }

    @Test
    fun `GIVEN Character WHEN mapReverse is called THEN CharacterDto is returned`() {
        // given
        val character = createCharacter()

        // when
        val result = characterDtoMapper.mapReverse(character)

        // then
        result equalTo createCharacterDto()
    }

    @Test
    fun `GIVEN a null id parameter WHEN map is called THEN mapped id is 0`() {
        // given
        val idParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                id = idParameter
            )
        )

        // then
        result.id equalTo 0
    }

    @Test
    fun `GIVEN a null name parameter WHEN map is called THEN mapped name is empty`() {
        // given
        val nameParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                name = nameParameter
            )
        )

        // then
        result.name equalTo ""
    }

    @Test
    fun `GIVEN a null path parameter WHEN map is called THEN mapped path is empty`() {
        // given
        val pathParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                path = pathParameter
            )
        )

        // then
        result.thumbnail.path equalTo ""
    }

    @Test
    fun `GIVEN a null extension parameter WHEN map is called THEN mapped extension is empty`() {
        // given
        val extensionParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                extension = extensionParameter
            )
        )

        // then
        result.thumbnail.extension equalTo ""
    }
}
