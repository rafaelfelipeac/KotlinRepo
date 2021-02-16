package com.rafaelfelipeac.marvelapp.features.commons.data

import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.persistence.sharedpreferences.Preferences
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListModeDataSourceTest {

    @Mock
    internal lateinit var preferences: Preferences

    private lateinit var listModeDataSource: ListModeDataSource

    @Before
    fun setup() {
        listModeDataSource = ListModeDataSource(preferences)
    }

    @Test
    fun `GIVEN a boolean value WHEN getListMode is called THEN return the same boolean value`() {
        runBlocking {
            // given
            val booleanValue = false

            given(preferences.listMode)
                .willReturn(booleanValue)

            // when
            val result = listModeDataSource.getListMode()

            // then
            result equalTo booleanValue
        }
    }

    @Test
    fun `GIVEN a saved boolean value WHEN getListMode is called THEN the same boolean value must be returned`() {
        runBlocking {
            // given
            val booleanValue = true

            doNothing().`when`(preferences).listMode = booleanValue
            given(preferences.listMode)
                .willReturn(booleanValue)

            // when
            val resultOfSave = listModeDataSource.saveListMode(booleanValue)
            val returnOfGet = listModeDataSource.getListMode()

            // then
            resultOfSave equalTo Unit
            returnOfGet equalTo booleanValue
        }
    }
}
