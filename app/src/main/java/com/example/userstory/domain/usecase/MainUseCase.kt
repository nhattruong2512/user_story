package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserInfo

interface MainUseCase {
    fun getUsers(
        perPage: Int,
        since: Int,
        onSuccess: (List<UserInfo>) -> Unit,
        onError: (Throwable) -> Unit
    )
}