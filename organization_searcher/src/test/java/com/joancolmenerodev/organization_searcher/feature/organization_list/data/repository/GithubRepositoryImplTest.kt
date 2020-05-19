package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GithubRepositoryImplTest {

    private lateinit var githubRepository: GithubRepository
    private val mockApi: GithubService = mockk()
    private val mockRepositoriesDao: RepositoriesDao = mockk()
    private val mockOrganizationDao: OrganizationDao = mockk()

    @Before
    fun setUp() {
        githubRepository =
            GithubRepositoryImpl(mockApi, mockRepositoriesDao, mockOrganizationDao)
    }

    @Test
    fun `given user requests repositories by organization that is not in the local storage, then returns one from network`(){
        //ASSIGN

        //ACT
        val result = runBlocking { mockApi.getRepositoriesByOrganization(ORGANIZATION_NAME) }

        //ASSERT


    }

    @Test
    fun `given user request repositories by organization that is in the local storage, then returns from local storage `(){
        //ASSIGN

        //ACT

        //ASSERT

    }

    companion object {
        private const val ORGANIZATION_NAME = "Xing"
    }



}