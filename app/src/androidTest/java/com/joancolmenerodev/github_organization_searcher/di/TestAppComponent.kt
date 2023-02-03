package com.joancolmenerodev.github_organization_searcher.di

import com.joancolmenerodev.github_organization_searcher.App
import com.joancolmenerodev.github_organization_searcher.di.modules.AppFeaturesModule
import com.joancolmenerodev.github_organization_searcher.di.modules.CoroutineDispatcherProviderModule
import com.joancolmenerodev.networking.di.TestNetworkingModule
import com.joancolmenerodev.persistence.di.TestPersistenceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestNetworkingModule::class,
        AppFeaturesModule::class,
        TestPersistenceModule::class,
        CoroutineDispatcherProviderModule::class]
)
interface TestAppComponent : AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): TestAppComponent
    }
}