package com.joancolmenerodev.persistence.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.joancolmenerodev.persistence.database.Database
import com.joancolmenerodev.persistence.entities.Organization
import com.joancolmenerodev.persistence.entities.Repository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoriesDaoTest {

    private lateinit var repositoriesDao: RepositoriesDao
    private lateinit var db: Database

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries()
            .build()
        repositoriesDao = db.repositoriesDao()
        runBlocking { db.organizationDao().insert(Organization(ORGANIZATION_NAME)) }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertRepository() = runBlocking {
        //ASSIGN

        //ACT
        repositoriesDao.insert(repository1)

        //ASSERT
        val repositoriesByName = repositoriesDao.findRepositoryByOrganization(ORGANIZATION_NAME)
        assertEquals(repositoriesByName.first().id, repository1.id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllRepositories() = runBlocking {
        //ASSIGN

        //ACT
        repositoriesDao.insert(repository1)
        repositoriesDao.insert(repository2)

        //ASSERT
        val repositoriesByName = repositoriesDao.findRepositoryByOrganization(ORGANIZATION_NAME)
        assertEquals(repositoriesByName.first().id, repository1.id)
        assertEquals(repositoriesByName.last().id, repository2.id)
    }

    @Test
    @Throws(Exception::class)
    fun deleteRepository() = runBlocking {
        //ASSIGN

        //ACT
        repositoriesDao.insert(repository1)
        repositoriesDao.insert(repository2)
        repositoriesDao.delete(repository1)

        //ASSERT
        val repositoriesByName = repositoriesDao.findRepositoryByOrganization(ORGANIZATION_NAME)
        assertEquals(repositoriesByName.size, ONE_ITEM_IN_THE_LIST)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun errorWhenInsertingARepositoryWithoutAddingTheOrganizationBefore() = runBlocking {
        //ASSIGN

        //ACT
        repositoriesDao.insert(repositoryError)

        //ASSERT
        fail()
    }

    companion object {
        private const val ORGANIZATION_NAME = "xing"
        private const val ONE_ITEM_IN_THE_LIST = 1
        private val repository1 = Repository(
            id = 1234,
            name = "Github-organization-searcher",
            description = "Best description ever",
            url = "https://github.com/xing/github-organization-searcher",
            forked = true,
            ownerName = "joancolmenerodev",
            ownerAvatar = "https://imgur.com/123",
            ownerURL = "https:://github.com/joancolmenerodev",
            organizationName = ORGANIZATION_NAME
        )
        private val repository2 = Repository(
            id = 12345,
            name = "Github-organization",
            description = "Best description ever",
            url = "https://github.com/xing/github-organization",
            forked = false,
            ownerName = "joancolmenerodev",
            ownerAvatar = "https://imgur.com/1235",
            ownerURL = "https:://github.com/joancolmenerodev",
            organizationName = ORGANIZATION_NAME
        )
        private val repositoryError = Repository(
            id = 12345,
            name = "Github-organization",
            description = "Best description ever",
            url = "https://github.com/xing/github-organization",
            forked = false,
            ownerName = "joancolmenerodev",
            ownerAvatar = "https://imgur.com/1235",
            ownerURL = "https:://github.com/joancolmenerodev",
            organizationName = "ORGANIZATION_FAIL"
        )
    }
}