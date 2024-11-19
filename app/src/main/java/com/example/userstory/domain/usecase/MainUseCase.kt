package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserInfo

interface MainUseCase {
    suspend fun getUsers(
        perPage: Int,
        since: Int
    ): List<UserInfo>
}