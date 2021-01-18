package com.rafaelfelipeac.kotlinrepo.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rafaelfelipeac.kotlinrepo.base.DataProviderAndroidTest.createRepositories
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.Repository
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.usecase.GetRepositoriesUseCase
import com.rafaelfelipeac.kotlinrepo.features.repositories.presentation.RepositoriesViewModel

class FakeRepositoriesViewModel(
    repositoriesUseCase: GetRepositoriesUseCase,
    private val result: Result
) : RepositoriesViewModel(repositoriesUseCase) {

    override val repositories: LiveData<List<Repository>?> get() = _repositories
    private val _repositories = MutableLiveData<List<Repository>?>()
    override val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    override fun getRepositories(language: String, sort: String, page: Int) {
        when (result) {
            Result.SUCCESS -> {
                _repositories.value = createRepositories()
            }
            Result.NETWORK_ERROR -> {
                _error.value = Exception()
            }
            Result.GENERIC_ERROR -> {
                _error.value = Exception()
            }
        }
    }

    enum class Result {
        SUCCESS,
        NETWORK_ERROR,
        GENERIC_ERROR
    }
}
