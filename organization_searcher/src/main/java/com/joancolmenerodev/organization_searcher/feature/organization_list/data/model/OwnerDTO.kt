package com.joancolmenerodev.organization_searcher.feature.organization_list.data.model


import com.google.gson.annotations.SerializedName

data class OwnerDTO(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("login")
    val login: String
)