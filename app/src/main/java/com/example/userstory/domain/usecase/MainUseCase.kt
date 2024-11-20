package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    suspend fun getUsers(
        perPage: Int,
        since: Int
    ): Flow<List<UserInfo>>
}