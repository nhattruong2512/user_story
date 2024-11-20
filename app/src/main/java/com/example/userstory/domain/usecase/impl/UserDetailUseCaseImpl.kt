package com.example.userstory.domain.usecase.impl

import com.example.userstory.data.datasource.local.toUserDetail
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.LocalRepository
import com.example.userstory.domain.repository.RestRepository
import com.example.userstory.domain.usecase.UserDetailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDetailUseCaseImpl @Inject constructor(
    private val restRepository: RestRepository,
    private val localRepository: LocalRepository
): UserDetailUseCase {

    override fun getUserDetail(loginName: String): Flow<UserDetail> = flow {
        val localUser = localRepository.getUser(loginName).firstOrNull()
        if (localUser != null) {
            emit(localUser.toUserDetail())
        }
        try {
            val remoteUser = restRepository.getUserDetail(loginName)
            localRepository.insertUser(remoteUser)
            emit(remoteUser)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}