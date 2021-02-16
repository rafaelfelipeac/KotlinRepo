package com.rafaelfelipeac.marvelapp.features.characters.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelfelipeac.marvelapp.base.CoroutineRule
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacters
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createFavorite
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockApiKey
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterId
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterName
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockCharacterUrl
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockHash
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockOffset
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockTimestamp
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveListModeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var mockGetCharactersUseCase = mock(GetCharactersUseCase::class.java)
    private var mockSaveFavoriteUseCase = mock(SaveFavoriteUseCase::class.java)
    private var mockSaveListModeUseCase = mock(SaveListModeUseCase::class.java)
    private var mockGetListModeUseCase = mock(GetListModeUseCase::class.java)

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        charactersViewModel = CharactersViewModel(
            mockGetCharactersUseCase,
            mockSaveFavoriteUseCase,
            mockSaveListModeUseCase,
            mockGetListModeUseCase
        )
    }

    @Test
    fun `GIVEN a Success result WHEN getCharacters is called THEN a list with characters should be returned`() {
        // given
        val characters = createCharacters()
        val success = ResultWrapper.Success(characters)

        given(runBlocking {
            mockGetCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )
        }).willReturn(success)

        // when
        charactersViewModel.getCharacters(mockApiKey, mockTimestamp, mockHash, mockOffset)

        // then
        charactersViewModel.characters.value equalTo characters
    }

    @Test
    fun `GIVEN a GenericError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val genericError = ResultWrapper.GenericError(null, null, throwable)

        given(runBlocking {
            mockGetCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )
        }).willReturn(genericError)

        // when
        charactersViewModel.getCharacters(mockApiKey, mockTimestamp, mockHash, mockOffset)

        // then
        runBlocking {
            charactersViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a NetworkError result WHEN getCharacters is called THEN a throwable should be returned`() {
        // given
        val throwable = Exception()
        val networkError = ResultWrapper.NetworkError(throwable)

        given(runBlocking {
            mockGetCharactersUseCase(
                mockApiKey,
                mockHash,
                mockTimestamp,
                mockOffset
            )
        }).willReturn(networkError)

        // when
        charactersViewModel.getCharacters(mockApiKey, mockTimestamp, mockHash, mockOffset)

        // then
        runBlocking {
            charactersViewModel.error.value equalTo throwable
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN favoriteCharacter is called THEN a characterId must be returned`() {
        // given
        val favorite = createFavorite(mockCharacterId, mockCharacterName, mockCharacterUrl)

        given(runBlocking {
            mockSaveFavoriteUseCase(
                favorite
            )
        }).willReturn(mockCharacterId)

        // when
        charactersViewModel.favoriteCharacter(favorite)

        // then
        runBlocking {
            charactersViewModel.savedFavorite.value equalTo mockCharacterId
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN saveListMode with true is called THEN a Unit is returned`() {
        // given
        val listMode = true

        given(runBlocking {
            mockSaveListModeUseCase(
                listMode
            )
        }).willReturn(Unit)

        // when
        charactersViewModel.saveListMode(listMode)

        // then
        runBlocking {
            charactersViewModel.savedListMode.value equalTo Unit
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN saveListMode with false is called THEN a Unit is returned`() {
        // given
        val listMode = false

        given(runBlocking {
            mockSaveListModeUseCase(
                listMode
            )
        }).willReturn(Unit)

        // when
        charactersViewModel.saveListMode(listMode)

        // then
        runBlocking {
            charactersViewModel.savedListMode.value equalTo Unit
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN getListMode with true is called THEN a a boolean should be returned`() {
        // given
        val listMode = true

        given(runBlocking {
            mockGetListModeUseCase(
            )
        }).willReturn(listMode)

        // when
        charactersViewModel.getListMode()

        // then
        runBlocking {
            charactersViewModel.listMode.value equalTo listMode
        }
    }

    @Test
    fun `GIVEN a successfully result WHEN getListMode with false is called THEN a boolean should be returned`() {
        // given
        val listMode = false

        given(runBlocking {
            mockGetListModeUseCase(
            )
        }).willReturn(listMode)

        // when
        charactersViewModel.getListMode()

        // then
        runBlocking {
            charactersViewModel.listMode.value equalTo listMode
        }
    }
}
