package com.rafaelfelipeac.marvelapp.features.details.data

import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {

    @GET("search/characters")
    suspend fun getDetails(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): CharacterListDto

    @GET("search/characters")
    suspend fun getDetailsComics(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): CharacterListDto

    @GET("search/characters")
    suspend fun getDetailsSeries(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): CharacterListDto
}
