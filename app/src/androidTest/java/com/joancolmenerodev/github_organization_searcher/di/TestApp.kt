package com.joancolmenerodev.github_organization_searcher.di

import com.joancolmenerodev.github_organization_searcher.App
import dagger.android.AndroidInjector
import okhttp3.mockwebserver.MockWebServer

class TestApp : App() {

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    lateinit var mockWebServer: MockWebServer
    override fun onCreate() {
        mockWebServer = MockWebServer()
        DaggerTestAppComponent.factory()
            .create(this)
            .inject(this)
    }
}