package com.rafaelfelipeac.marvelapp.features.characters.data

import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("search/characters")
    suspend fun getAllCharacters(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): CharacterListDto
}
