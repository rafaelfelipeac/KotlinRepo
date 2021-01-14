package com.rafaelfelipeac.githubrepositories.features.repositories.domain.model

data class Repository(
    val name: String,
    val stars: Int,
    val forks: Int,
    val authorImage: String,
    val authorName: String
)
