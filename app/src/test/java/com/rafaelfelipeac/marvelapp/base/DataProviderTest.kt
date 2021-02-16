package com.rafaelfelipeac.marvelapp.base

import com.rafaelfelipeac.marvelapp.features.characters.data.model.ThumbnailDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import com.rafaelfelipeac.marvelapp.features.characters.data.model.MarvelCharacterListDto
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Thumbnail
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.CharacterList
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.MarvelCharacterList
import com.rafaelfelipeac.marvelapp.features.commons.data.model.FavoriteDto
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.details.data.model.MarvelCharacterDetailDto
import com.rafaelfelipeac.marvelapp.features.details.data.model.MarvelDetailInfoDto
import com.rafaelfelipeac.marvelapp.features.details.data.model.CharacterDetailDto
import com.rafaelfelipeac.marvelapp.features.details.data.model.CharacterDetailListDto
import com.rafaelfelipeac.marvelapp.features.details.data.model.DetailInfoDto
import com.rafaelfelipeac.marvelapp.features.details.data.model.DetailInfoListDto
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetailList
import com.rafaelfelipeac.marvelapp.features.details.domain.model.MarvelCharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.model.MarvelDetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfoList
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo

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

    const val mockCharacterDetailId = 1L
    const val mockCharacterDetailName = ""
    const val mockCharacterDetailDescription = ""
    const val mockCharacterDetailUrl = 1L

    const val mockDetailInfoId = 1L
    const val mockDetailInfoTitle = ""

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
            createThumbnailDtoCharacter(path, extension)
        )
    }

    fun createThumbnailDtoCharacter(
        path: String? = mockPath,
        extension: String? = mockExtension
    ): ThumbnailDto {
        return ThumbnailDto(path, extension)
    }

    fun createThumbnailDtoDetails(
        path: String? = mockPath,
        extension: String? = mockExtension
    ): com.rafaelfelipeac.marvelapp.features.details.data.model.ThumbnailDto {
        return com.rafaelfelipeac.marvelapp.features.details.data.model.ThumbnailDto(
            path,
            extension
        )
    }

    fun createMarvelCharacterListDto(): MarvelCharacterListDto {
        return MarvelCharacterListDto(createCharacterListDto())
    }

    fun createFavoriteDto(
        favoriteId: Long = mockFavoriteId,
        favoriteName: String = mockFavoriteName,
        favoriteUrl: String = mockFavoriteUrl
    ): FavoriteDto {
        return FavoriteDto(favoriteId, favoriteName, favoriteUrl)
    }

    fun createCharacterDetailDto(): CharacterDetailDto {
        return CharacterDetailDto(
            mockCharacterDetailId,
            mockCharacterDetailName,
            mockCharacterDetailDescription,
            createThumbnailDtoDetails()
        )
    }

    fun createCharacterDetailListDto(): CharacterDetailListDto {
        return CharacterDetailListDto(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createCharacterDetailDto(), createCharacterDetailDto(), createCharacterDetailDto())
        )
    }

    fun createDetailInfoDto(): DetailInfoDto {
        return DetailInfoDto(
            mockDetailInfoId,
            mockDetailInfoTitle,
            createThumbnailDtoDetails()
        )
    }

    fun createDetailInfoListDto(): DetailInfoListDto {
        return DetailInfoListDto(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createDetailInfoDto(), createDetailInfoDto(), createDetailInfoDto())
        )
    }

    fun createMarvelCharacterDetailDto(): MarvelCharacterDetailDto {
        return MarvelCharacterDetailDto(
            createCharacterDetailListDto()
        )
    }

    fun createMarvelDetailInfoDto(): MarvelDetailInfoDto {
        return MarvelDetailInfoDto(
            createDetailInfoListDto()
        )
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
            createThumbnailCharacter(path, extension)
        )
    }

    fun createThumbnailCharacter(
        path: String = mockPath,
        extension: String = mockExtension
    ): Thumbnail {
        return Thumbnail(path, extension)
    }

    fun createThumbnailDetails(
        path: String = mockPath,
        extension: String = mockExtension
    ): com.rafaelfelipeac.marvelapp.features.details.domain.model.Thumbnail {
        return com.rafaelfelipeac.marvelapp.features.details.domain.model.Thumbnail(path, extension)
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

    fun createCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            mockCharacterDetailId,
            mockCharacterDetailName,
            mockCharacterDetailDescription,
            createThumbnailDetails()
        )
    }

    fun createCharacterDetailList(): CharacterDetailList {
        return CharacterDetailList(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createCharacterDetail(), createCharacterDetail(), createCharacterDetail())
        )
    }

    fun createDetailInfo(): DetailInfo {
        return DetailInfo(
            mockDetailInfoId,
            mockDetailInfoTitle,
            createThumbnailDetails()
        )
    }

    fun createDetailInfoList(): DetailInfoList {
        return DetailInfoList(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
        )
    }

    fun createMarvelCharacterDetail(): MarvelCharacterDetail {
        return MarvelCharacterDetail(
            createCharacterDetailList()
        )
    }

    fun createMarvelDetailInfo(): MarvelDetailInfo {
        return MarvelDetailInfo(
            createDetailInfoList()
        )
    }
    // endregion
}
