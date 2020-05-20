package com.joancolmenerodev.organization_searcher.feature.organization_list.di

import com.joancolmenerodev.organization_searcher.feature.organization_list.presentation.RepositoryByOrganizationSearcherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OrganizationSearcherModule {

    @ContributesAndroidInjector(modules = [RepositoryByOrganizationSearcherDependenciesModule::class])
    abstract fun bindRepositoryByOrganizationSearcherActivity(): RepositoryByOrganizationSearcherActivity
}