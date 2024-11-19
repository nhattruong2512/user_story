package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserDetail

interface UserDetailUseCase {
    suspend fun getUserDetail(loginName: String): UserDetail
}