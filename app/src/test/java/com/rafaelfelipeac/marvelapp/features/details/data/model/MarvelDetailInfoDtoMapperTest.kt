package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelDetailInfo
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createMarvelDetailInfoDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class MarvelDetailInfoDtoMapperTest {

    private val marvelDetailInfoDtoMapper = MarvelDetailInfoDtoMapper()

    @Test
    fun `GIVEN MarvelDetailInfo WHEN mapReverse is called THEN MarvelDetailInfoDto is returned`() {
        // given
        val marvelDetailInfo = createMarvelDetailInfo()

        // when
        val result = marvelDetailInfoDtoMapper.mapReverse(marvelDetailInfo)

        // then
        result equalTo createMarvelDetailInfoDto()
    }
}
