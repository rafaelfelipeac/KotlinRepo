package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun loadData(language: String, sort: String, page: Int) {
        getRepositories(language, sort, page)
    }

    private fun getRepositories(language: String, sort: String, page: Int) {
        viewModelScope.launch {
            _repositories.value = getRepositoriesUseCase(language, sort, page)
        }
    }
}
