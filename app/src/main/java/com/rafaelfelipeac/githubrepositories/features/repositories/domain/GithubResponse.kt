package com.rafaelfelipeac.githubrepositories.features.repositories.domain

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @SerializedName("items")
    val items: List<ItemResponse>,
)

data class ItemResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("forks_count")
    val fork: Int,
    @SerializedName("owner")
    val author: OwnerResponse
)

data class OwnerResponse(
    @SerializedName("login")
    val authorName: String,
    @SerializedName("avatar_url")
    val authorAvatar: String,
)
