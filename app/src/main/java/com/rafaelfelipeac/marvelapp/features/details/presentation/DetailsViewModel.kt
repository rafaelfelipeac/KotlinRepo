package com.rafaelfelipeac.marvelapp.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.core.extension.md5
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.core.plataform.Config.API_KEY
import com.rafaelfelipeac.marvelapp.core.plataform.Config.PRIVATE_KEY
import com.rafaelfelipeac.marvelapp.features.details.domain.model.CharacterDetail
import com.rafaelfelipeac.marvelapp.features.details.domain.model.DetailInfo
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsComicsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.SaveFavoriteUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

open class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val getDetailsComicsUseCase: GetDetailsComicsUseCase,
    private val getDetailsSeriesUseCase: GetDetailsSeriesUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {

    open val details: LiveData<CharacterDetail?> get() = _details
    private val _details = MutableLiveData<CharacterDetail?>()
    open val comics: LiveData<List<DetailInfo>?> get() = _comics
    private val _comics = MutableLiveData<List<DetailInfo>?>()
    open val series: LiveData<List<DetailInfo>?> get() = _series
    private val _series = MutableLiveData<List<DetailInfo>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    open val errorComics: LiveData<Throwable> get() = _errorComics
    private val _errorComics = MutableLiveData<Throwable>()
    open val errorSeries: LiveData<Throwable> get() = _errorSeries
    private val _errorSeries = MutableLiveData<Throwable>()
    val savedFavorite: LiveData<Long?> get() = _savedFavorite
    private val _savedFavorite = MutableLiveData<Long?>()

    open fun getDetails(characterId: Long) {
        val timestamp = Date().time
        val hash = (timestamp.toString() + PRIVATE_KEY + API_KEY).md5()

        viewModelScope.launch {
            when (val response = getDetailsUseCase(characterId, API_KEY, hash, timestamp)) {
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

    open fun getDetailsComics(characterId: Long, offset: Int) {
        val timestamp = Date().time
        val hash = (timestamp.toString() + PRIVATE_KEY + API_KEY).md5()

        viewModelScope.launch {
            when (val response = getDetailsComicsUseCase.invoke(characterId, API_KEY, hash, timestamp, offset)) {
                is ResultWrapper.Success -> {
                    _comics.value = response.value
                }
                is ResultWrapper.GenericError -> {
                    _errorComics.value = response.throwable
                }
                is ResultWrapper.NetworkError -> {
                    _errorComics.value = response.throwable
                }
            }
        }
    }

    open fun getDetailsSeries(characterId: Long, offset: Int) {
        val timestamp = Date().time
        val hash = (timestamp.toString() + PRIVATE_KEY + API_KEY).md5()

        viewModelScope.launch {
            when (val response = getDetailsSeriesUseCase.invoke(characterId, API_KEY, hash, timestamp, offset)) {
                is ResultWrapper.Success -> {
                    _series.value = response.value
                }
                is ResultWrapper.GenericError -> {
                    _errorSeries.value = response.throwable
                }
                is ResultWrapper.NetworkError -> {
                    _errorSeries.value = response.throwable
                }
            }
        }
    }

    open fun favoriteCharacter(characterId: Long, characterName: String, characterUrl: String) {
        viewModelScope.launch {
            _savedFavorite.value = saveFavoriteUseCase(characterId, characterName, characterUrl)
        }
    }
}
