package com.rafaelfelipeac.marvelapp.features.characters.domain.usecase

import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.features.commons.domain.repository.ListModeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetListModeUseCaseTest {

    @Mock
    internal lateinit var mockListModeRepository: ListModeRepository

    private lateinit var getListModeUseCase: GetListModeUseCase

    @Before
    fun setup() {
        getListModeUseCase = GetListModeUseCase(mockListModeRepository)
    }

    @Test
    fun `GIVEN a successfully result WHEN getListModeUseCase is called THEN return the list mode as false'`() {
        runBlocking {
            // given
            val listMode = false

            given(mockListModeRepository.getListMode())
                .willReturn(listMode)

            // when
            val result = getListModeUseCase()

            // then
            result equalTo listMode
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN getListModeUseCase is called THEN return the list mode as true'`() {
        runBlocking {
            // given
            val listMode = true

            given(mockListModeRepository.getListMode())
                .willReturn(listMode)

            // when
            val result = getListModeUseCase()

            // then
            result equalTo listMode
        }
    }
}
