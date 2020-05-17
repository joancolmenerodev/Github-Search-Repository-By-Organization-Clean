package com.joancolmenerodev.organization_searcher.feature.organization_list.data.model

class RepositoriesByOrganizationResponse : ArrayList<RepositoriesByOrganizationResponseItem>()

fun RepositoriesByOrganizationResponse.map() = this.map { it.map() }