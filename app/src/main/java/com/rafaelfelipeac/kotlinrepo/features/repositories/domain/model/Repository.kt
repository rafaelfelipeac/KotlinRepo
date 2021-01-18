package com.rafaelfelipeac.kotlinrepo.features.repositories.domain.model

data class Repository(
    val name: String,
    val stars: Int,
    val forks: Int,
    val owner: Owner
)