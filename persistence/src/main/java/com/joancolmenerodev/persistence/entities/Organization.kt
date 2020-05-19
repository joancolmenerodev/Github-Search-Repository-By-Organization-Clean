package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_ORGANIZATION")
data class Organization(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String
)