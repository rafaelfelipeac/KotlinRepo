package com.rafaelfelipeac.marvelapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rafaelfelipeac.marvelapp.base.DataProviderAndroidTest.createCharacters
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersViewModel

class FakeFavoriteViewModel(
    charactersUseCase: GetCharactersUseCase,
    private val result: Result
) : CharactersViewModel(charactersUseCase) {

    override val characters: LiveData<List<Character>?> get() = _characters
    private val _characters = MutableLiveData<List<Character>?>()
    override val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()

    override fun getCharacters(apiKey: String, hash: String, ts: Int) {
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

    enum class Result {
        SUCCESS,
        NETWORK_ERROR,
        GENERIC_ERROR
    }
}
