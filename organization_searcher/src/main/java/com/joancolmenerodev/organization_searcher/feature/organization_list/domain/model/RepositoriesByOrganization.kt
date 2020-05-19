package com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model

import com.joancolmenerodev.persistence.entities.Repository

data class RepositoriesByOrganization(
    val id: Int,
    val name: String?,
    val description: String?,
    val url: String?,
    val forked: Boolean,
    val owner_name: String?,
    val owner_avatar: String?,
    val owner_url: String
)


fun RepositoriesByOrganization.map(organization: String): Repository {
    return Repository(
        this.id,
        this.name,
        this.description,
        this.url,
        this.forked,
        this.owner_name,
        this.owner_avatar,
        this.owner_url,
        organization
    )

}