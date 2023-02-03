package com.joancolmenerodev.organization_searcher.feature.organization_list.domain.usecase

import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.library_base.usecase.BaseUseCase
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.GithubRepository
import javax.inject.Inject

class GetRepositoriesByOrganizationUseCase @Inject constructor(private val repository: GithubRepository): BaseUseCase() {

    suspend fun execute(organization: String): Either<RepositoriesByOrganizationExceptions, List<RepositoriesByOrganization>> =
        toEither { repository.getRepositoriesByOrganization(organization) }

}