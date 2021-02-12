package com.rafaelfelipeac.marvelapp.core.di.provider

import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersViewModel

interface ViewModelProvider {

    fun charactersViewModel(): CharactersViewModel
}
