package com.rafaelfelipeac.marvelapp.features.commons.data

import com.rafaelfelipeac.marvelapp.core.persistence.sharedpreferences.Preferences
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListModeDataSource @Inject constructor(
    private val preferences: Preferences
) : ListModeRepository {

    override suspend fun saveListMode(listMode: Boolean) {
        return withContext(Dispatchers.IO) {
            preferences.listMode = listMode
        }
    }

    override suspend fun getListMode(): Boolean {
        return withContext(Dispatchers.IO) {
            preferences.listMode
        }
    }
}
