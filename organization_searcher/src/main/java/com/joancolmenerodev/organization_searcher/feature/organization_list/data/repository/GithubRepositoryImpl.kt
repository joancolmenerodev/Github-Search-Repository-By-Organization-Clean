package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.library_base.exceptions.ClientException
import com.joancolmenerodev.library_base.repository.BaseRepository
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.map
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val service: GithubService) :
    GithubRepository, BaseRepository() {

    override suspend fun getRepositoriesByOrganization(organization: String): List<RepositoriesByOrganization> =
        execute {
            try {
                service.getRepositoriesByOrganization(organization).map { it.map() }
            } catch (exception: Exception) {
                when (exception) {
                    is ClientException.NotFound -> throw RepositoriesByOrganizationExceptions.OrganizationNotFound
                    else -> throw RepositoriesByOrganizationExceptions.ListNotAvailable
                }
            }
        }
}