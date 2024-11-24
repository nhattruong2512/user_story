package com.example.userstory.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("login")
    val login: String,

    @ColumnInfo("avatar_url")
    val avatarUrl: String,

    @ColumnInfo("html_url")
    val htmlUrl: String
)
