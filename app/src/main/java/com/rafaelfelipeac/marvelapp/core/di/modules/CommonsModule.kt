package com.rafaelfelipeac.marvelapp.core.di.modules

import com.rafaelfelipeac.marvelapp.features.commons.data.FavoriteDataSource
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module

@Module
abstract class CommonsModule {

    @Binds
    abstract fun favoriteRepository(favoriteDataSource: FavoriteDataSource): FavoriteRepository
}
