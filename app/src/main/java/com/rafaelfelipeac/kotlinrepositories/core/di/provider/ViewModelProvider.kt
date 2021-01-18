package com.rafaelfelipeac.kotlinrepositories.core.di.provider

import com.rafaelfelipeac.kotlinrepositories.features.repositories.presentation.RepositoriesViewModel

interface ViewModelProvider {

    fun repositoriesViewModel(): RepositoriesViewModel
}
