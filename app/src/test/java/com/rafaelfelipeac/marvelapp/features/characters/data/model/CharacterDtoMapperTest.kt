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
    fun `GIVEN a null stars parameter WHEN map is called THEN mapped stars is empty`() {
        // given
        val starsParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                stars = starsParameter
            )
        )

        // then
        result.stars equalTo 0
    }

    @Test
    fun `GIVEN a null forks parameter WHEN map is called THEN mapped forks is empty`() {
        // given
        val forksParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                forks = forksParameter
            )
        )

        // then
        result.forks equalTo 0
    }

    @Test
    fun `GIVEN a null login parameter WHEN map is called THEN mapped ownerName is empty`() {
        // given
        val loginParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                login = loginParameter
            )
        )

        // then
        result.thumbnail.path equalTo ""
    }

    @Test
    fun `GIVEN a null avatarUrl parameter WHEN map is called THEN mapped ownerAvatarUrl is empty`() {
        // given
        val avatarUrlParameter = null

        // when
        val result = characterDtoMapper.map(
            createCharacterDto(
                avatar_url = avatarUrlParameter
            )
        )

        // then
        result.thumbnail.extension equalTo ""
    }
}
