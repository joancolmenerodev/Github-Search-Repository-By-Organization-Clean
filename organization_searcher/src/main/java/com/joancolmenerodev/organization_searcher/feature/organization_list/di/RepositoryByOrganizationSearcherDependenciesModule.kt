package com.joancolmenerodev.organization_searcher.feature.organization_list.di

import com.joancolmenerodev.organization_searcher.feature.organization_list.data.repository.GithubRepositoryImpl
import com.joancolmenerodev.organization_searcher.feature.organization_list.domain.GithubRepository
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp.RepositoryByOrganizationContract
import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.mvp.RepositoryByOrganizationPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryByOrganizationSearcherDependenciesModule {

    @Binds
    abstract fun bindRepositoryByOrganizationSearcherPresenter(presenter: RepositoryByOrganizationPresenter): RepositoryByOrganizationContract.Presenter

    @Binds
    abstract fun bindGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}