package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization

interface GithubRepository {

    suspend fun getRepositoriesByOrganization(organization: String): List<RepositoriesByOrganization>
}