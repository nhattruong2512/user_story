package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserDetail

interface UserDetailUseCase {
    fun getUserDetail(
        loginName: String,
        onSuccess: (UserDetail) -> Unit,
        onError: (Throwable) -> Unit
    )
}