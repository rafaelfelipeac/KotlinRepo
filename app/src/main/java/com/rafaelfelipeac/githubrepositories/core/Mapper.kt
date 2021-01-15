package com.rafaelfelipeac.githubrepositories.core

interface Mapper<InType, OutType> {
    fun map(param: InType): OutType

    fun mapList(param: List<InType>): List<OutType> = param.map(::map)
}
