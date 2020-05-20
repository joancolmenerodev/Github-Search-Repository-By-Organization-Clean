package com.joancolmenerodev.organization_searcher.feature.organization_list.data.mapper

import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.model.RepositoriesByOrganization
import com.joancolmenerodev.persistence.entities.Repository
import junit.framework.Assert.assertEquals
import org.junit.Test


class EntitiesMapperKtTest {

    @Test
    fun `Repository to RepositoriesByOrganization mapper`() {
        //ASSIGN
        val repository = Repository(
            id = 1234,
            name = "Github-organization-searcher",
            description = "Best description ever",
            url = "https://github.com/xing/github-organization-searcher",
            forked = true,
            ownerName = "joancolmenerodev",
            ownerAvatar = "https://imgur.com/123",
            ownerURL = "https:://github.com/joancolmenerodev",
            organizationName = "Xing"
        )
        val expectedResult = RepositoriesByOrganization(
            id = 1234,
            name = "Github-organization-searcher",
            description = "Best description ever",
            url = "https://github.com/xing/github-organization-searcher",
            forked = true,
            owner_name = "joancolmenerodev",
            owner_avatar = "https://imgur.com/123",
            owner_url = "https:://github.com/joancolmenerodev"
        )

        //ACT
        val result = repository.map()

        //ASSERT
        assertEquals(result, expectedResult)

    }
}