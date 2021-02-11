package com.rafaelfelipeac.marvelapp.features.repositories.data

import com.rafaelfelipeac.marvelapp.features.repositories.data.model.RepositoryListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {

    @GET("search/repositories")
    suspend fun getAllRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): RepositoryListDto
}
