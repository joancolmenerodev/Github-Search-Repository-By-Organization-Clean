package com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository

import com.joancolmenerodev.organization_searcher.feature.organization_list.data.model.RepositoriesByOrganizationResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("orgs/{organization}/repos")
    suspend fun getRepositoriesByOrganization(@Path("organization") organization: String): RepositoriesByOrganizationResponse

}