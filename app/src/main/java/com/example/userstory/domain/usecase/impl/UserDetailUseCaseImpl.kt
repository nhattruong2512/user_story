package com.example.userstory.domain.usecase.impl

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.LocalRepository
import com.example.userstory.domain.repository.RestRepository
import com.example.userstory.domain.usecase.UserDetailUseCase
import javax.inject.Inject

class UserDetailUseCaseImpl @Inject constructor(
    private val restRepositoryImpl: RestRepository,
    private val localRepository: LocalRepository
): UserDetailUseCase {
    override fun getUserDetail(
        loginName: String,
        onSuccess: (UserDetail) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        restRepositoryImpl.getUserDetail(loginName)
    }

}