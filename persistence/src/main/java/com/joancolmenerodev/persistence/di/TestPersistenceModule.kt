package com.joancolmenerodev.persistence.di

import android.content.Context
import androidx.room.Room
import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import com.joancolmenerodev.persistence.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestPersistenceModule {

    @Provides
    @Singleton
    fun provideTestRoomDatabase(app: Context): Database {
        return Room.inMemoryDatabaseBuilder(app, Database::class.java).allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositoriesDao(database: Database): RepositoriesDao = database.repositoriesDao()

    @Provides
    @Singleton
    fun provideOrganizationDao(database: Database): OrganizationDao = database.organizationDao()
}