package com.joancolmenerodev.organization_searcher.feature.organization_list.data.model


import com.google.gson.annotations.SerializedName
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization

data class RepositoriesByOrganizationResponseItem(

    @SerializedName("description")
    val description: String,
    @SerializedName("forks")
    val fork: Int,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val ownerDTO: OwnerDTO
)

fun RepositoriesByOrganizationResponseItem.map(): RepositoriesByOrganization {
    return RepositoriesByOrganization(
        id = this.id,
        name = this.name,
        description = this.description,
        url = this.htmlUrl,
        forked = this.fork > 0,
        owner_name = this.ownerDTO.login,
        owner_avatar = this.ownerDTO.avatarUrl,
        owner_url = this.ownerDTO.htmlUrl
    )
}