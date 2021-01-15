package com.rafaelfelipeac.githubrepositories.features.repositories.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.githubrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.Owner
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
    val genericError: Flow<Unit> get() = _genericError.filterNotNull()
    private val _genericError = MutableStateFlow<Unit?>(null)
    val networkError: Flow<Unit> get() = _networkError.filterNotNull()
    private val _networkError = MutableStateFlow<Unit?>(null)

    fun loadData(language: String, sort: String, page: Int) {
        getRepositories(language, sort, page)
    }

    private fun getRepositories(language: String, sort: String, page: Int) {
        viewModelScope.launch {
            when (val response = getRepositoriesUseCase(language, sort, page)) {
                is ResultWrapper.Success -> {
                    val list: MutableList<Repository> = mutableListOf()

                    response.value.items.map {
                        list.add(
                                Repository(
                                        it.name,
                                        it.stars,
                                        it.fork,
                                        Owner(it.author.login, it.author.avatar_url)
                                )
                        )
                    }

                    _repositories.value = list
                }
                is ResultWrapper.GenericError -> {
                    _genericError.value = Unit
                }
                is ResultWrapper.NetworkError -> {
                    _networkError.value = Unit
                }
            }
        }
    }
}
