package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.library_base.exceptions.ServiceException
import com.joancolmenerodev.organization_searcher.IntegrationTest
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.OwnerDTO
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.RepositoriesByOrganizationResponse
import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.RepositoriesByOrganizationResponseItem
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.HttpURLConnection

class GithubServiceApiIntegrationTest : IntegrationTest() {

    @Test
    fun `given that the repositories list is called then it returns success`() {
        // ASSIGN
        val expectedResult = repositoriesByOrganizationResponse
        mockHttpResponse("repositories_by_organization_response.json", HttpURLConnection.HTTP_OK)

        // ACT
        val response = runBlocking {
            apiService.getRepositoriesByOrganization(ORGANIZATION_NAME)
        }

        // ASSERT
        assertEquals(expectedResult.toString(), response.toString())
    }

    @Test(expected = ServiceException::class)
    fun `given that the repositories list return a failure then it returns failure`() {
        // ASSIGN
        mockHttpResponse("repositories_by_organization_response.json", HttpURLConnection.HTTP_UNAVAILABLE)

        // ACT
        runBlocking {
            apiService.getRepositoriesByOrganization(ORGANIZATION_NAME)
        }
    }

    companion object {
        private const val ORGANIZATION_NAME = "XING"
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
