package com.rafaelfelipeac.marvelapp.features.details.data.model

import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfoList
import com.rafaelfelipeac.marvelapp.base.DataProviderTest.createDetailInfoListDto
import com.rafaelfelipeac.marvelapp.base.equalTo
import org.junit.Test

class DetailInfoListDtoMapperTest {

    private val detailInfoListDtoMapper = DetailInfoListDtoMapper()

    @Test
    fun `GIVEN DetailInfoListDto WHEN map is called THEN DetailInfoList is returned`() {
        // given
        val detailInfoListDto = createDetailInfoListDto()

        // when
        val result = detailInfoListDtoMapper.map(detailInfoListDto)

        // then
        result equalTo createDetailInfoList()
    }

    @Test
    fun `GIVEN DetailInfoList WHEN mapReverse is called THEN DetailInfoListDto is returned`() {
        // given
        val detailInfoList = createDetailInfoList()

        // when
        val result = detailInfoListDtoMapper.mapReverse(detailInfoList)

        // then
        result equalTo createDetailInfoListDto()
    }
}
