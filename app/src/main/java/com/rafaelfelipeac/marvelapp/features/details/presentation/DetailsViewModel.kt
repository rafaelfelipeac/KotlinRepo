package com.rafaelfelipeac.marvelapp.features.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.model.Character
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsComicsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

open class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val getDetailsComicsUseCase: GetDetailsComicsUseCase,
    private val getDetailsSeriesUseCase: GetDetailsSeriesUseCase
) : ViewModel() {

    open val details: LiveData<List<Character>?> get() = _details
    private val _details = MutableLiveData<List<Character>?>()
    open val comics: LiveData<List<Character>?> get() = _comics
    private val _comics = MutableLiveData<List<Character>?>()
    open val series: LiveData<List<Character>?> get() = _series
    private val _series = MutableLiveData<List<Character>?>()
    open val error: LiveData<Throwable> get() = _error
    private val _error = MutableLiveData<Throwable>()
    open val errorComics: LiveData<Throwable> get() = _errorComics
    private val _errorComics = MutableLiveData<Throwable>()
    open val errorSeries: LiveData<Throwable> get() = _errorSeries
    private val _errorSeries = MutableLiveData<Throwable>()

    open fun getDetails(apikey: String, hash: String, ts: Long) {
        viewModelScope.launch {
            when (val response = getDetailsUseCase(apikey, hash, ts)) {
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

    open fun getDetailsComics(apikey: String, hash: String, ts: Long) {
        viewModelScope.launch {
            when (val response = getDetailsComicsUseCase(apikey, hash, ts)) {
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

    open fun getDetailsSeries(apikey: String, hash: String, ts: Long) {
        viewModelScope.launch {
            when (val response = getDetailsSeriesUseCase(apikey, hash, ts)) {
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
}
