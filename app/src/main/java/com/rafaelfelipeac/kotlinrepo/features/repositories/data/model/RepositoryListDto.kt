package com.rafaelfelipeac.kotlinrepo.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.kotlinrepo.core.TwoWayMapper
import com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model.RepositoryList
import javax.inject.Inject

data class RepositoryListDto(
    @SerializedName("items")
    val items: List<RepositoryDto>
)

class RepositoryListDtoMapper @Inject constructor() : TwoWayMapper<RepositoryListDto, RepositoryList> {

    override fun map(param: RepositoryListDto): RepositoryList = with(param) {
        RepositoryList(
            items = RepositoryDtoMapper().mapList(items)
        )
    }

    override fun mapReverse(param: RepositoryList): RepositoryListDto = with(param) {
        RepositoryListDto(
            items = RepositoryDtoMapper().mapListReverse(items)
        )
    }
}