package com.rafaelfelipeac.marvelapp.features.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoritesUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.SaveListModeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val saveListModeUseCase: SaveListModeUseCase,
    private val getListModeUseCase: GetListModeUseCase
) : ViewModel() {

    open val favorites: LiveData<List<Favorite>?> get() = _favorites
    private val _favorites = MutableLiveData<List<Favorite>?>()
    open val deleted: LiveData<Unit?> get() = _deleted
    private val _deleted = MutableLiveData<Unit?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    val listMode: LiveData<Boolean?> get() = _listMode
    private val _listMode = MutableLiveData<Boolean?>()
    val savedListMode: LiveData<Unit?> get() = _savedListMode
    private val _savedListMode = MutableLiveData<Unit?>()

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

    open fun saveListMode(listMode: Boolean) {
        viewModelScope.launch {
            _savedListMode.value = saveListModeUseCase(listMode)
        }
    }

    open fun getListMode() {
        viewModelScope.launch {
            _listMode.value = getListModeUseCase()
        }
    }
}
