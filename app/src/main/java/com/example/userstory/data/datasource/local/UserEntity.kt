package com.example.userstory.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("id")
    var login: String,

    @ColumnInfo("avatar_url")
    var avatarUrl: String,

    @ColumnInfo("html_url")
    var htmlUrl: String,

    @ColumnInfo("location")
    var location: String,

    @ColumnInfo("followers")
    var followers: Int,

    @ColumnInfo("following")
    var following: Int
)
