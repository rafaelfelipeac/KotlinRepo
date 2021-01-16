package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
        private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    val repositories: Flow<List<Repository>> get() = _repositories.filterNotNull()
    private val _repositories = MutableStateFlow<List<Repository>?>(null)
    val error: Flow<Throwable> get() = _error.filterNotNull()
    private val _error = MutableStateFlow<Throwable?>(null)

    fun getRepositories(language: String, sort: String, page: Int) {
        viewModelScope.launch {
            when (val response = getRepositoriesUseCase(language, sort, page)) {
                is ResultWrapper.Success -> {
                    _repositories.value = response.value
                }
                is ResultWrapper.GenericError -> {
                    _error.value = response.throwable
                }
                is ResultWrapper.NetworkError -> {
                    _error.value = response.throwable
                }
            }
        }
    }
}
