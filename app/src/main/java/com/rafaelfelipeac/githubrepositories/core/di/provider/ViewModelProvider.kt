package com.rafaelfelipeac.githubrepositories.core.di.provider

import com.rafaelfelipeac.githubrepositories.features.repositories.presentation.RepositoriesViewModel

interface ViewModelProvider {

    fun repositoriesViewModel(): RepositoriesViewModel
}
