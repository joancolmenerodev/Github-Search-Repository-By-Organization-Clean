package com.joancolmenerodev.organization_searcher.feature.organization_list.data.model

import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoriesByOrganizationResponseItemMapperTest {

    @Test
    fun `repositories by organization response item mapper returns the object mapped`() {

        //ARRANGE
        val expectedResult = listOf(
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

        //ACT

        val result = repoItems.map { it.map() }

        //ASSERT

        assertEquals(result, expectedResult)

    }

    companion object {

        val repoItems = listOf(
            RepositoriesByOrganizationResponseItem(
                description = "Description of repo 1",
                fork = true,
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
                fork = false,
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
    }
}