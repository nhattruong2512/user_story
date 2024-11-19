package com.example.userstory.domain.repository

import com.example.userstory.domain.model.UserDetail

interface LocalRepository {
    suspend fun putUserDetail(user: UserDetail)
    suspend fun getUser(loginName: String): UserDetail
}