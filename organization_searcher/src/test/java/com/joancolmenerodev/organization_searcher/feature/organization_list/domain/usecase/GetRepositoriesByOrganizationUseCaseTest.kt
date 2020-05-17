package com.joancolmenerodev.organization_searcher.feature.organization_list.domain.usecase

import com.joancolmenerodev.library_base.Either
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository.GithubRepository
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetRepositoriesByOrganizationUseCaseTest {

    private lateinit var useCase: GetRepositoriesByOrganizationUseCase
    private val repository: GithubRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetRepositoriesByOrganizationUseCase(repository)
    }

    @Test
    fun `given the repository returns the list of repositories then the result is a list of repositories`() {
        //ASSIGN
        coEvery { repository.getRepositoriesByOrganization(any()) } answers { reposList }

        //ACT
        val result = runBlocking { useCase.execute(ORGANIZATION_NAME) }

        //ASSERT
        assertEquals(result, Either.Right(reposList))

    }

    @Test
    fun `given the repository returns a Not Found then the result is an Either Organization Not Found`() {
        //ASSIGN
        val organizationNotFound = RepositoriesByOrganizationExceptions.OrganizationNotFound
        coEvery { repository.getRepositoriesByOrganization(any()) } throws organizationNotFound

        //ACT
        val result = runBlocking { useCase.execute(ORGANIZATION_NAME) }

        //ASSERT
        assertEquals(result, Either.Left(organizationNotFound))

    }

    @Test
    fun `given the repository returns an List not available then the result is an Either List not available`() {
        //ASSIGN
        val listNotAvailable = RepositoriesByOrganizationExceptions.ListNotAvailable
        coEvery { repository.getRepositoriesByOrganization(any()) } throws listNotAvailable

        //ACT
        val result = runBlocking { useCase.execute(ORGANIZATION_NAME) }

        //ASSERT
        assertEquals(result, Either.Left(listNotAvailable))

    }

    companion object {
        const val ORGANIZATION_NAME = "XING"
        val reposList = listOf(
            RepositoriesByOrganization(
                id = 1,
                name = "Repository 1",
                description = "Description of repo 1",
                url = "https://wwww.repo1.com",
                forked = true,
                owner_name = "Joan",
                owner_avatar = "https://www.avatar_of_joan.com",
                owner_url = "https://www.joan.com"
            ),
            RepositoriesByOrganization(
                id = 2,
                name = "Repository 2",
                description = "Description of repo 2",
                url = "https://wwww.repo2.com",
                forked = false,
                owner_name = "Gustavo",
                owner_avatar = "https://www.avatar_of_gustavo.com",
                owner_url = "https://www.gustavo.com"
            )
        )
    }

}