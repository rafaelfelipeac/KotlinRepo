package com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase

import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetListModeUseCaseTest {

    @Mock
    internal lateinit var mockListModeRepository: ListModeRepository

    private lateinit var getListModeUseCaseTest: GetListModeUseCase

    @Before
    fun setup() {
        getListModeUseCaseTest = GetListModeUseCase(mockListModeRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN getListModeUseCase is called THEN return the list mode as false'`() {
        runBlocking {
            // given
            val listMode = false

            BDDMockito.given(mockListModeRepository.getListMode())
                .willReturn(listMode)

            // when
            val result = getListModeUseCaseTest()

            // then
            result equalTo listMode
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN getListModeUseCase is called THEN return the list mode as true'`() {
        runBlocking {
            // given
            val listMode = true

            BDDMockito.given(mockListModeRepository.getListMode())
                .willReturn(listMode)

            // when
            val result = getListModeUseCaseTest()

            // then
            result equalTo listMode
        }
    }
}