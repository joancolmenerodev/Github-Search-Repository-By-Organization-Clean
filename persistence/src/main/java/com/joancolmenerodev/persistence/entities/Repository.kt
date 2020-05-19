package com.joancolmenerodev.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "TB_REPOSITORY",
    foreignKeys = [ForeignKey(
        entity = Organization::class,
        parentColumns = ["name"],
        childColumns = ["organization_name"],
        onDelete = CASCADE
    )]
)
data class Repository(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "repository_id")
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "forked")
    var forked: Boolean = false,

    @ColumnInfo(name = "owner_name")
    var ownerName: String? = null,

    @ColumnInfo(name = "owner_avatar")
    var ownerAvatar: String? = null,

    @ColumnInfo(name = "ownerURL")
    var ownerURL: String,

    @ColumnInfo(name = "organization_name")
    val organizationName: String
) {
}