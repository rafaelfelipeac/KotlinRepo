package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    open val favorites: LiveData<List<Favorite>?> get() = _favorites
    private val _favorites = MutableLiveData<List<Favorite>?>()
    open val deleted: LiveData<Unit?> get() = _deleted
    private val _deleted = MutableLiveData<Unit?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoritesUseCase()
        }
    }

    open fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            _deleted.value = deleteFavoriteUseCase(favorite)
        }
    }
}
