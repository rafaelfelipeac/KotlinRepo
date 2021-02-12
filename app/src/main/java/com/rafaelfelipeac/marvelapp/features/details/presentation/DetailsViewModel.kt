package com.rafaelfelipeac.marvelapp.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    open val details: LiveData<List<Character>?> get() = _details
    private val _details = MutableLiveData<List<Character>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getDetails(language: String, sort: String, page: Int) {
        viewModelScope.launch {
            when (val response = getDetailsUseCase(language, sort, page)) {
                is ResultWrapper.Success -> {
                    _details.value = response.value
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
