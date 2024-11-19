package com.example.userstory.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userstory.domain.model.UserDetail

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id LIKE :loginName")
    suspend fun getUser(loginName: String): UserEntity
}