package com.rafaelfelipeac.githubrepositories.features.repositories.data.model

import com.google.gson.annotations.SerializedName
import com.rafaelfelipeac.githubrepositories.core.Mapper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.model.RepositoryList
import javax.inject.Inject

data class RepositoryListDto (
    @SerializedName("items")
    val items: List<RepositoryDto>
)

class RepositoryListDtoMapper @Inject constructor() : Mapper<RepositoryListDto, RepositoryList> {

    override fun map(param: RepositoryListDto): RepositoryList = with(param) {
        RepositoryList(
            items = RepositoryDtoMapper().mapList(items)
        )
    }
}
