package com.rafaelfelipeac.marvelapp.core.di.provider

import com.rafaelfelipeac.marvelapp.features.repositories.presentation.RepositoriesViewModel

interface ViewModelProvider {

    fun repositoriesViewModel(): RepositoriesViewModel
}
