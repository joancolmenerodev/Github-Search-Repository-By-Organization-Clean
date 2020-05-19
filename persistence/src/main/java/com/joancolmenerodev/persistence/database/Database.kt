package com.joancolmenerodev.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joancolmenerodev.persistence.dao.OrganizationDao
import com.joancolmenerodev.persistence.dao.RepositoriesDao
import com.joancolmenerodev.persistence.entities.Organization
import com.joancolmenerodev.persistence.entities.Repository

@Database(entities = [Repository::class, Organization::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao
    abstract fun organizationDao(): OrganizationDao

}