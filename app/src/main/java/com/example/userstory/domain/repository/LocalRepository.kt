package com.example.userstory.domain.repository

import com.example.userstory.domain.model.UserDetail

interface LocalRepository {
    fun putUserDetail(user: UserDetail)
    fun getUser(loginName: String): UserDetail
}