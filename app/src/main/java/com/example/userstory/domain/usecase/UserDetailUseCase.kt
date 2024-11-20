package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserDetailUseCase {
     fun getUserDetail(loginName: String): Flow<UserDetail>
}