package com.rafaelfelipeac.kotlinrepositories.features.repositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.kotlinrepositories.core.network.ResultWrapper
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.model.Repository
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    open val repositories: LiveData<List<Repository>?> get() = _repositories
    private val _repositories = MutableLiveData<List<Repository>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getRepositories(language: String, sort: String, page: Int) {
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
