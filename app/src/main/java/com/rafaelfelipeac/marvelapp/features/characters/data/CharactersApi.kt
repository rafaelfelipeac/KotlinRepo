package com.rafaelfelipeac.marvelapp.features.characters.data

import com.rafaelfelipeac.marvelapp.features.characters.data.model.MarvelCharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("characters")
    suspend fun getAllCharacters(
            @Query("apikey") apiKey: String,
            @Query("hash") hash: String,
            @Query("ts") ts: Long,
            @Query("offset") offset: Int
    ): MarvelCharacterListDto
}
