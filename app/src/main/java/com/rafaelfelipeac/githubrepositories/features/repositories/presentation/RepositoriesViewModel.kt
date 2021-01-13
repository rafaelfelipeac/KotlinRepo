package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import androidx.lifecycle.ViewModel
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase.GetRepositoriesUseCase
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

}
