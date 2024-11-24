package com.example.userstory.domain.repository

import androidx.paging.PagingData
import com.example.userstory.data.local.UserEntity
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserEntity>>
    fun getUserDetail(loginName: String): Flow<UserDetail>
}