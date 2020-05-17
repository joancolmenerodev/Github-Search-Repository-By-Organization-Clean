package com.joancolmenerodev.github_organization_searcher.di.modules

import com.joancolmenerodev.organization_searcher.feature.organization_list.di.OrganizationSearcherModule
import dagger.Module

@Module(includes = [OrganizationSearcherModule::class])
object AppFeaturesModule