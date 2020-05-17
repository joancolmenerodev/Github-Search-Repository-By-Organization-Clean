package com.joancolmenerodev.networking.retrofit

import com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository.GithubService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object RetrofitServiceModule {

    @Provides
    @Reusable
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

}