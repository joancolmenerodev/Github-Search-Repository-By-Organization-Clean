package com.joancolmenerodev.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.joancolmenerodev.persistence.entities.Repository

@Dao
interface RepositoriesDao : BaseDao<Repository> {

    @Query("SELECT * FROM TB_REPOSITORY WHERE LOWER(organization_name)=:organization")
    suspend fun findRepositoryByOrganization(organization: String): List<Repository>

}