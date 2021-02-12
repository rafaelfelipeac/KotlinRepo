package com.rafaelfelipeac.marvelapp.features.favorites.data

import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FavoriteDao {

    @GET("search/characters")
    suspend fun getFavorite(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): CharacterListDto
}
