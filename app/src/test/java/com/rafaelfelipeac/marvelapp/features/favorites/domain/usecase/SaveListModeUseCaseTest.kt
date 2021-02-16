package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveListModeUseCase
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveListModeUseCaseTest {

    @Mock
    internal lateinit var mockListModeRepository: ListModeRepository

    private lateinit var saveListModeUseCase: SaveListModeUseCase

    @Before
    fun setup() {
        saveListModeUseCase = SaveListModeUseCase(mockListModeRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN saveListModeUseCase is called with false THEN return a Unit'`() {
        runBlocking {
            // given
            val listMode = true

            given(mockListModeRepository.saveListMode(listMode))
                .willReturn(Unit)

            // when
            val result = saveListModeUseCase(listMode)

            // then
            result equalTo Unit
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN saveListModeUseCase is called with true THEN return a Unit'`() {
        runBlocking {
            // given
            val listMode = false

            given(mockListModeRepository.saveListMode(listMode))
                .willReturn(Unit)

            // when
            val result = saveListModeUseCase(listMode)

            // then
            result equalTo Unit
        }
    }
}
