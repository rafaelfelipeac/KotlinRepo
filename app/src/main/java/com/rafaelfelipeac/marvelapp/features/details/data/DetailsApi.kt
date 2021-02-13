package com.rafaelfelipeac.marvelapp.features.details.data

import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {

    @GET("search/characters")
    suspend fun getDetails(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): CharacterListDto

    @GET("search/characters")
    suspend fun getDetailsComics(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): CharacterListDto

    @GET("search/characters")
    suspend fun getDetailsSeries(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Long
    ): CharacterListDto
}
