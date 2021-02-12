package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    open val favorites: LiveData<List<Character>?> get() = _favorites
    private val _favorites = MutableLiveData<List<Character>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getFavorites(language: String, sort: String, page: Int) {
        viewModelScope.launch {
            when (val response = getFavoriteUseCase(language, sort, page)) {
                is ResultWrapper.Success -> {
                    _favorites.value = response.value
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
