package com.rafaelfelipeac.marvelapp.core.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.rafaelfelipeac.marvelapp.core.network.CacheInterceptor
import com.rafaelfelipeac.marvelapp.features.characters.data.CharactersApi
import com.rafaelfelipeac.marvelapp.features.details.data.DetailsApi
import com.rafaelfelipeac.marvelapp.features.favorites.data.FavoriteDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class CoreModule {

    companion object {

        @Provides
        fun gson(): Gson = Gson()

        @Provides
        fun Retrofit.charactersApi(): CharactersApi = create()

        @Provides
        fun Retrofit.detailsApi(): DetailsApi = create()

        @Provides
        fun Retrofit.favoriteDao(): FavoriteDao = create()

        @Provides
        fun cacheInterceptor(): CacheInterceptor = CacheInterceptor()
    }

    @Binds
    abstract fun Application.context(): Context
}
