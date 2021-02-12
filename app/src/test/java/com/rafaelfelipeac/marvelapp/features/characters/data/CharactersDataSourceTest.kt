package com.rafaelfelipeac.marvelapp.features.characters.data

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createCharacterListDto
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockLanguage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockPage
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.mockSort
import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.network.ResultWrapper
import com.rafaelfelipeac.marvelapp.features.characters.data.model.CharacterDtoMapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class CharactersDataSourceTest {

    @Mock
    internal lateinit var charactersApi: CharactersApi

    @Mock
    internal lateinit var characterDtoMapper: CharacterDtoMapper

    private lateinit var charactersDataSource: CharactersDataSource

    @Before
    fun setup() {
        charactersDataSource = CharactersDataSource(charactersApi, characterDtoMapper)
    }

    @Test
    fun `GIVEN Success return from api WHEN getAllCharacters is called THEN Success is returned`() {
        runBlocking {
            // given
            val characterListDto = createCharacterListDto()
            val characters = characterListDto.items.map { characterDtoMapper.map(it) }
            val success = ResultWrapper.Success(characters)

            given(
                charactersApi.getAllCharacters(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willReturn(characterListDto)

            // when
            val result = charactersDataSource.getCharacters(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo success
        }
    }

    @Test
    fun `GIVEN NetworkError return from api WHEN getAllCharacters is called THEN NetworkError is returned`() {
        runBlocking {
            // given
            val throwable = IOException()
            val networkError = ResultWrapper.NetworkError(throwable)

            given(
                charactersApi.getAllCharacters(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willAnswer {
                throw throwable
            }

            // when
            val result = charactersDataSource.getCharacters(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo networkError
        }
    }

    @Test
    fun `GIVEN GeneticError return from api WHEN getAllCharacters is called THEN GenericError is returned`() {
        runBlocking {
            // given
            val throwable = Exception()
            val genericError = ResultWrapper.GenericError(null, null, throwable)

            given(
                charactersApi.getAllCharacters(
                    mockLanguage,
                    mockSort,
                    mockPage
                )
            ).willAnswer {
                throw throwable
            }

            // when
            val result = charactersDataSource.getCharacters(
                mockLanguage,
                mockSort,
                mockPage
            )

            // then
            result equalTo genericError
        }
    }
}
