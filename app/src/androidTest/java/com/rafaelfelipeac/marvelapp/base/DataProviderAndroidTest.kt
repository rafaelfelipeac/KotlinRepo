package com.rafaelfelipeac.marvelapp.base

import com.rafaelfelipeac.marvelapp.features.characters.data.model.ThumbnailDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Thumbnail
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.CharacterList

object DataProviderAndroidTest {

    const val mockLanguage = "language:kotlin"
    const val mockSort = "star"
    const val mockPage = 1

    const val mockOwnerName = "Owner name"
    const val mockOwnerAvatarUrl =
        "https://180dc.org/wp-content/uploads/2017/11/profile-placeholder.png"

    const val mockCharacterName = "Character Name"
    const val mockCharacterStars = 200
    const val mockCharacterForks = 100

    // region Data
    fun createCharacterListDto(): CharacterListDto {
        return CharacterListDto(createCharactersDto())
    }

    fun createCharactersDto(): List<CharacterDto> {
        return listOf(
            createCharacterDto(),
            createCharacterDto(),
            createCharacterDto()
        )
    }

    fun createCharacterDto(
        name: String? = mockCharacterName,
        stars: Int? = mockCharacterStars,
        forks: Int? = mockCharacterForks,
        login: String? = mockOwnerName,
        avatar_url: String? = mockOwnerAvatarUrl
    ): CharacterDto {
        return CharacterDto(
            name,
            stars,
            forks,
            createOwnerDto(login, avatar_url)
        )
    }

    fun createOwnerDto(
        login: String? = mockOwnerName,
        avatar_url: String? = mockOwnerAvatarUrl
    ): ThumbnailDto {
        return ThumbnailDto(login, avatar_url)
    }
    // endregion

    // region Domain
    fun createCharacterList(): CharacterList {
        return CharacterList(createCharacters())
    }

    fun createCharacters(): List<Character> {
        return listOf(
            createCharacter(),
            createCharacter(),
            createCharacter()
        )
    }

    fun createCharacter(
        name: String = mockCharacterName,
        stars: Int = mockCharacterStars,
        forks: Int = mockCharacterForks,
        ownerName: String = mockOwnerName,
        ownerAvatarUrl: String = mockOwnerAvatarUrl
    ): Character {
        return Character(
            name,
            stars,
            forks,
            createOwner(ownerName, ownerAvatarUrl)
        )
    }

    fun createOwner(
        name: String = mockOwnerName,
        avatarUrl: String = mockOwnerAvatarUrl
    ): Thumbnail {
        return Thumbnail(name, avatarUrl)
    }
    // endregion
}
