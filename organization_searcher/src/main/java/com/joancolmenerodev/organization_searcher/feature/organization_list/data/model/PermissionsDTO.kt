package com.joancolmenerodev.organization_searcher.feature.organization_list.data.model


import com.google.gson.annotations.SerializedName

data class PermissionsDTO(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean
)