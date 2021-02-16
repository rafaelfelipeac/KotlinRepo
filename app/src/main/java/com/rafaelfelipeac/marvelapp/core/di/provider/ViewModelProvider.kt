package com.rafaelfelipeac.marvelapp.core.di.provider

import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersViewModel
import com.rafaelfelipeac.marvelapp.features.details.presentation.DetailsViewModel
import com.rafaelfelipeac.marvelapp.features.favorites.presentation.FavoritesViewModel

interface ViewModelProvider {

    fun charactersViewModel(): CharactersViewModel

    fun detailsViewModel(): DetailsViewModel

    fun favoriteViewModel(): FavoritesViewModel
}
