package com.joancolmenerodev.github_organization_searcher.di.modules

import android.content.Context
import com.joancolmenerodev.github_organization_searcher.App
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(application: App): Context = application
}