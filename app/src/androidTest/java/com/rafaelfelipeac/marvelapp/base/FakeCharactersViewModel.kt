package com.rafaelfelipeac.marvelapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.base.DataProviderAndroidTest.createCharacters
import com.rafaelfelipeac.marvelapp.base.DataProviderAndroidTest.mockFavoriteId
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveListModeUseCase
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersViewModel
import com.rafaelfelipeac.marvelapp.features.commons.domain.model.Favorite
import kotlinx.coroutines.launch

open class FakeCharactersViewModel(
    charactersUseCase: GetCharactersUseCase,
    saveFavoriteUseCase: SaveFavoriteUseCase,
    saveListModeUseCase: SaveListModeUseCase,
    getListModeUseCase: GetListModeUseCase,
    private val result: Result
) : CharactersViewModel(
    charactersUseCase,
    saveFavoriteUseCase,
    saveListModeUseCase,
    getListModeUseCase
) {

    override val characters: LiveData<List<Character>?> get() = _characters
    private val _characters = MutableLiveData<List<Character>?>()
    override val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    override val savedFavorite: LiveData<Long?> get() = _savedFavorite
    private val _savedFavorite = MutableLiveData<Long?>()
    override val listMode: LiveData<Boolean?> get() = _listMode
    private val _listMode = MutableLiveData<Boolean?>()
    override val savedListMode: LiveData<Unit?> get() = _savedListMode
    private val _savedListMode = MutableLiveData<Unit?>()

    override fun getCharacters(apikey: String, timestamp: Long, hash: String, offset: Int) {
        when (result) {
            Result.SUCCESS -> {
                _characters.value = createCharacters()
            }
            Result.NETWORK_ERROR -> {
                _error.value = Exception()
            }
            Result.GENERIC_ERROR -> {
                _error.value = Exception()
            }
        }
    }

    override fun favoriteCharacter(favorite: Favorite) {
        viewModelScope.launch {
            _savedFavorite.value = mockFavoriteId
        }
    }

    override fun saveListMode(listMode: Boolean) {
        viewModelScope.launch {
            _savedListMode.value = Unit
        }
    }

    override fun getListMode() {
        viewModelScope.launch {
            _listMode.value = true
        }
    }

    enum class Result {
        SUCCESS,
        NETWORK_ERROR,
        GENERIC_ERROR
    }
}
