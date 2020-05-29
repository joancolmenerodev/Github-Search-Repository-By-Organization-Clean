package com.joancolmenerodev.persistence.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import com.joancolmenerodev.persistence.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "sample_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRepositoriesDao(database: Database): RepositoriesDao = database.repositoriesDao()

    @Provides
    fun provideOrganizationDao(database: Database): OrganizationDao = database.organizationDao()
}