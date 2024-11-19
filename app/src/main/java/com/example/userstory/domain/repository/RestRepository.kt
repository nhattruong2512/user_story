package com.example.userstory.domain.repository

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo

interface RestRepository {
    suspend fun getUsers(perPage: Int, since: Int): List<UserInfo>
    suspend fun getUserDetail(loginName: String): UserDetail
}