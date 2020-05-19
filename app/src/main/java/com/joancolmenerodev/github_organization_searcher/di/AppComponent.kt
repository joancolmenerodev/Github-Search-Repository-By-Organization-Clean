package com.joancolmenerodev.github_organization_searcher.di

import com.joancolmenerodev.github_organization_searcher.App
import com.joancolmenerodev.github_organization_searcher.di.modules.AppFeaturesModule
import com.joancolmenerodev.github_organization_searcher.di.modules.AppModule
import com.joancolmenerodev.github_organization_searcher.di.modules.CoroutineDispatcherProviderModule
import com.joancolmenerodev.networking.di.NetworkingModule
import com.joancolmenerodev.persistence.di.PersistenceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkingModule::class,
        PersistenceModule::class,
        AppFeaturesModule::class,
        CoroutineDispatcherProviderModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): AppComponent
    }
}