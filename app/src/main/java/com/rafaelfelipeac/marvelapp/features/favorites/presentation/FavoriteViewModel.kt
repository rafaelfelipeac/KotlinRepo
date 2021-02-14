package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoriteUseCase
) : ViewModel() {

    open val favorites: LiveData<List<Favorite>?> get() = _favorites
    private val _favorites = MutableLiveData<List<Favorite>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoritesUseCase()
        }
    }
}
