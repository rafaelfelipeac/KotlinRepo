package com.rafaelfelipeac.marvelapp.features.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    open val characters: LiveData<List<Character>?> get() = _characters
    private val _characters = MutableLiveData<List<Character>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    open fun getCharacters(apiKey: String, hash: String, ts: Long) {
        viewModelScope.launch {
            when (val response = getCharactersUseCase(apiKey, hash, ts)) {
                is ResultWrapper.Success -> {
                    _characters.value = response.value
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
