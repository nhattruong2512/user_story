package com.example.userstory.domain.repository

import androidx.paging.PagingSource
import com.example.userstory.data.datasource.local.UserEntity
import com.example.userstory.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertUser(user: UserDetail)
    suspend fun insertUsers(users: List<UserDetail>)
    fun getUser(loginName: String): Flow<UserEntity>
    fun getUsers(): PagingSource<Int, UserEntity>
}