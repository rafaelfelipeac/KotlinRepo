package com.rafaelfelipeac.kotlinrepo.core.di.provider

import com.rafaelfelipeac.kotlinrepo.features.repositories.presentation.RepositoriesViewModel

interface ViewModelProvider {

    fun repositoriesViewModel(): RepositoriesViewModel
}
