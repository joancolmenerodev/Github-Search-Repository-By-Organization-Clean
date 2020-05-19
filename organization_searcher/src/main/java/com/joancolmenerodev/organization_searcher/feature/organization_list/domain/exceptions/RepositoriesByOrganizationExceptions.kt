package com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions

sealed class RepositoriesByOrganizationExceptions : Exception() {
    object OrganizationNotFound : RepositoriesByOrganizationExceptions()
    object ListNotAvailable : RepositoriesByOrganizationExceptions()
    object NoInternet : RepositoriesByOrganizationExceptions()
}