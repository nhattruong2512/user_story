package com.example.userstory.domain.repository

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo

interface RestRepository {
    fun getUsers(perPage: Int, since: Int): List<UserInfo>
    fun getUserDetail(loginName: String): UserDetail
}