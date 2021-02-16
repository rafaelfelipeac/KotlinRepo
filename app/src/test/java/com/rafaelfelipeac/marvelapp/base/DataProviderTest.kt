package com.rafaelfelipeac.marvelapp.base

import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.MarvelCharacterListDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.ThumbnailDto
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.CharacterList
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.MarvelCharacterList
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Thumbnail
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite

object DataProviderTest {

    const val mockApiKey = ""
    const val mockHash = ""
    const val mockTimestamp = 0L

    const val mockPath = "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_xlarge"
    const val mockExtension = ".jpg"

    const val mockCharacterId = 1L
    const val mockCharacterName = "Character Name"
    const val mockCharacterUrl = "Character Url"

    const val mockOffset = 0
    const val mockLimit = 20
    const val mockTotal = 100
    const val mockCount = 20

    const val mockFavoriteId = 1L
    const val mockFavoriteName = ""
    const val mockFavoriteUrl = ""

    // region Data
    fun createCharacterListDto(): CharacterListDto {
        return CharacterListDto(mockOffset, mockLimit, mockTotal, mockCount, createCharactersDto())
    }

    fun createCharactersDto(): List<CharacterDto> {
        return listOf(
            createCharacterDto(),
            createCharacterDto(),
            createCharacterDto()
        )
    }

    fun createCharacterDto(
        id: Long? = mockCharacterId,
        name: String? = mockCharacterName,
        path: String? = mockPath,
        extension: String? = mockExtension
    ): CharacterDto {
        return CharacterDto(
            id,
            name,
            createThumbnailDto(path, extension)
        )
    }

    fun createThumbnailDto(
        path: String? = mockPath,
        extension: String? = mockExtension
    ): ThumbnailDto {
        return ThumbnailDto(path, extension)
    }

    fun createMarvelCharacterListDto(): MarvelCharacterListDto {
        return MarvelCharacterListDto(createCharacterListDto())
    }
    // endregion

    // region Domain
    fun createCharacterList(): CharacterList {
        return CharacterList(mockOffset, mockLimit, mockTotal, mockCount, createCharacters())
    }

    fun createCharacters(): List<Character> {
        return listOf(
            createCharacter(),
            createCharacter(),
            createCharacter()
        )
    }

    fun createCharacter(
        id: Long = mockCharacterId,
        name: String = mockCharacterName,
        path: String = mockPath,
        extension: String = mockExtension
    ): Character {
        return Character(
            id,
            name,
            createThumbnail(path, extension)
        )
    }

    fun createThumbnail(
        path: String = mockPath,
        extension: String = mockExtension
    ): Thumbnail {
        return Thumbnail(path, extension)
    }

    fun createMarvelCharacterList(): MarvelCharacterList {
        return MarvelCharacterList(createCharacterList())
    }

    fun createFavorite(
        favoriteId: Long = mockFavoriteId,
        favoriteName: String = mockFavoriteName,
        favoriteUrl: String = mockFavoriteUrl
    ): Favorite {
        return Favorite(favoriteId, favoriteName, favoriteUrl)
    }
    // endregion
}
