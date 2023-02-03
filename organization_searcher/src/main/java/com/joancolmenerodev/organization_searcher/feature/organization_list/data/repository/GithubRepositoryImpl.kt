package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.library_base.exceptions.ClientException
import com.joancolmenerodev.library_base.repository.BaseRepository
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.mapper.map
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.map
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.map
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.repository.GithubRepository
import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import com.joancolmenerodev.persistence.entities.Organization
import com.joancolmenerodev.persistence.entities.Repository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val service: GithubService,
    private val repositoriesDao: RepositoriesDao,
    private val organizationDao: OrganizationDao
) :
    GithubRepository, BaseRepository() {


    override suspend fun getRepositoriesByOrganization(organization: String): List<RepositoriesByOrganization> {
        return execute {
            val organizationLowerCase = organization.toLowerCase()
            try {
                fetchRemoteData(organizationLowerCase)
            } catch (exception: Exception) {
                when (exception) {
                    is ClientException.NotFound -> throw RepositoriesByOrganizationExceptions.OrganizationNotFound
                    else -> {
                        fetchLocalData(organization)
                    }
                }
            }
        }
    }


    private suspend fun fetchLocalData(organization: String): List<RepositoriesByOrganization> {
        val findRepositoryByOrganization =
            repositoriesDao.findRepositoryByOrganization(organization)
        return if (findRepositoryByOrganization.isNotEmpty()) {
            findRepositoryByOrganization.map { it.map() }
        } else {
            throw RepositoriesByOrganizationExceptions.ListNotAvailable
        }
    }


    private suspend fun fetchRemoteData(organization: String): List<RepositoriesByOrganization> {
        val result = service.getRepositoriesByOrganization(organization)
        if (result.isNotEmpty()) {
            insertOrganization(organization)
        }
        val remoteData = result.map { it.map() }
        remoteData.forEach { insertRepositories(it.map(organization)) }
        return remoteData

    }

    private suspend fun insertOrganization(organizationName: String) = organizationDao.insert(
        Organization(organizationName)
    )

    private suspend fun insertRepositories(repository: Repository) =
        repositoriesDao.insert(repository)
}