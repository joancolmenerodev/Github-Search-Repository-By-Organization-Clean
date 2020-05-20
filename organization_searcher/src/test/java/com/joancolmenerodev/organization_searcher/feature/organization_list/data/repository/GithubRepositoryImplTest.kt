package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.library_base.exceptions.ClientException
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.OwnerDTO
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.RepositoriesByOrganizationResponse
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.RepositoriesByOrganizationResponseItem
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.exceptions.RepositoriesByOrganizationExceptions
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import com.joancolmenerodev.persistence.entities.Repository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class GithubRepositoryImplTest {

    private lateinit var githubRepository: GithubRepository
    private val mockService: GithubService = mockk()
    private val mockRepositoriesDao: RepositoriesDao = mockk()
    private val mockOrganizationDao: OrganizationDao = mockk()

    @Before
    fun setUp() {
        githubRepository =
            GithubRepositoryImpl(mockService, mockRepositoriesDao, mockOrganizationDao)
    }

    @Test
    fun `given user asks for an organization repositories and there's internet connection then it returns the list of repositories`() {

        //ASSIGN
        coEvery { mockService.getRepositoriesByOrganization(any()) } answers { repositoriesByOrganizationResponse }
        coEvery { mockOrganizationDao.insert(any()) } answers { Long.MAX_VALUE }
        coEvery { mockRepositoriesDao.insert(any()) } returnsMany (listOf(Long.MAX_VALUE))
        val expectedResult = repositoriesByOrganizationList

        //ACT
        val result =
            runBlocking { githubRepository.getRepositoriesByOrganization(ORGANIZATION_NAME) }

        //ASSERT
        coVerify(exactly = 2) {
            mockRepositoriesDao.insert(any())
        }
        coVerify {
            mockOrganizationDao.insert(any())
        }
        assertEquals(expectedResult, result)


    }

    @Test
    fun `given user asks for an organization repositories and there's internet connection but there's no repositories for that organization then returns empty list`() {

        //ASSIGN
        coEvery { mockService.getRepositoriesByOrganization(any()) } answers {
            RepositoriesByOrganizationResponse()
        }

        //ACT
        val result =
            runBlocking { githubRepository.getRepositoriesByOrganization(ORGANIZATION_NAME) }

        //ASSERT
        coVerify(exactly = 0) {
            mockRepositoriesDao.insert(any())
            mockOrganizationDao.insert(any())
        }
        assertTrue(result.isEmpty())

    }

    @Test(expected = RepositoriesByOrganizationExceptions.OrganizationNotFound::class)
    fun `given user asks for an organization repositories and there's internet connection but there's no organization with that name, then returns OrganizationNotFoundException`() {

        //ASSIGN
        val returnedException = ClientException.NotFound
        coEvery { mockService.getRepositoriesByOrganization(any()) } throws returnedException

        //ACT
        runBlocking { githubRepository.getRepositoriesByOrganization(ORGANIZATION_NAME) }

    }

    @Test
    fun `given user asks for an organization repositories but there's no internet connection it tries to get it on local storage and it returns the correct data`() {

        //ASSIGN
        coEvery { mockService.getRepositoriesByOrganization(any()) } throws ClientException.RequestTimeout
        coEvery { mockRepositoriesDao.findRepositoryByOrganization(any()) } answers { localListRepositories }
        val expectedResult = repositoriesByOrganizationList

        //ACT
        val result =
            runBlocking { githubRepository.getRepositoriesByOrganization(ORGANIZATION_NAME) }

        //ASSERT
        coVerify {
            mockRepositoriesDao.findRepositoryByOrganization(any())
        }
        assertEquals(expectedResult, result)

    }

    @Test(expected = RepositoriesByOrganizationExceptions.ListNotAvailable::class)
    fun `given user asks for an organization repositories but there's no internet connection it tries to get it on local storage and then it does not find anything then return ListNotAvailableException`() {

        //ASSIGN
        coEvery { mockService.getRepositoriesByOrganization(any()) } throws ClientException.RequestTimeout
        coEvery { mockRepositoriesDao.findRepositoryByOrganization(any()) } throws RepositoriesByOrganizationExceptions.ListNotAvailable

        //ACT
        runBlocking { githubRepository.getRepositoriesByOrganization(ORGANIZATION_NAME) }

        //ASSERT
        coVerify {
            mockRepositoriesDao.findRepositoryByOrganization(any())
        }
    }

    companion object {
        private const val ORGANIZATION_NAME = "Xing"

        private val repositoriesByOrganizationList = listOf(
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
        private val localListRepositories = listOf(
            Repository(
                id = 1,
                name = "Repository 1",
                description = "Description of repo 1",
                url = "https://wwww.repo1.com",
                forked = true,
                ownerName = "Joan",
                ownerAvatar = "https://www.avatar_of_joan.com",
                ownerURL = "https://www.joan.com",
                organizationName = "XING"
            ),
            Repository(
                id = 2,
                name = "Repository 2",
                description = "Description of repo 2",
                url = "https://wwww.repo2.com",
                forked = false,
                ownerName = "Gustavo",
                ownerAvatar = "https://www.avatar_of_gustavo.com",
                ownerURL = "https://www.gustavo.com",
                organizationName = "XING"
            )
        )
        private val repositoriesByOrganizationResponseItem = listOf(
            RepositoriesByOrganizationResponseItem(
                description = "Description of repo 1",
                fork = 10,
                htmlUrl = "https://wwww.repo1.com",
                id = 1,
                name = "Repository 1",
                ownerDTO = OwnerDTO(
                    avatarUrl = "https://www.avatar_of_joan.com",
                    htmlUrl = "https://www.joan.com",
                    login = "Joan"

                )
            ),
            RepositoriesByOrganizationResponseItem(
                description = "Description of repo 2",
                fork = 0,
                htmlUrl = "https://wwww.repo2.com",
                id = 2,
                name = "Repository 2",
                ownerDTO = OwnerDTO(
                    avatarUrl = "https://www.avatar_of_gustavo.com",
                    htmlUrl = "https://www.gustavo.com",
                    login = "Gustavo"

                )
            )
        )
        private val repositoriesByOrganizationResponse =
            RepositoriesByOrganizationResponse().apply {
                addAll(
                    repositoriesByOrganizationResponseItem
                )
            }

    }


}