package com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model

data class RepositoriesByOrganization(
    val id: Int,
    val name: String?,
    val description: String?,
    val url: String?,
    val forked: Boolean?,
    val owner_name: String?,
    val owner_avatar: String?,
    val owner_url: String
)