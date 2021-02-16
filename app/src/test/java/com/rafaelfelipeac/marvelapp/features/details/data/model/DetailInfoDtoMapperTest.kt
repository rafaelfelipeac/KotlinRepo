package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfo
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfoDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class DetailInfoDtoMapperTest {
    private val detailInfoDtoMapper = DetailInfoDtoMapper()

    @Test
    fun `GIVEN DetailInfoDto WHEN map is called THEN DetailInfo is returned`() {
        // given
        val detailInfoDto = createDetailInfoDto()

        // when
        val result = detailInfoDtoMapper.map(detailInfoDto)

        // then
        result equalTo createDetailInfo()
    }

    @Test
    fun `GIVEN DetailInfo WHEN mapReverse is called THEN DetailInfoDto is returned`() {
        // given
        val detailInfo = createDetailInfo()

        // when
        val result = detailInfoDtoMapper.mapReverse(detailInfo)

        // then
        result equalTo createDetailInfoDto()
    }
}
