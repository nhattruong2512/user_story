package com.example.userstory.domain.usecase.impl

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.LocalRepository
import com.example.userstory.domain.repository.RestRepository
import com.example.userstory.domain.usecase.UserDetailUseCase
import javax.inject.Inject

class UserDetailUseCaseImpl @Inject constructor(
    private val restRepository: RestRepository,
    private val localRepository: LocalRepository
): UserDetailUseCase {
    override suspend fun getUserDetail(loginName: String): UserDetail {
        return restRepository.getUserDetail(loginName)
    }

}