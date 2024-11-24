package com.example.userstory.domain.usecase

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(login: String): Flow<UserDetail> {
        return userRepository.getUserDetail(login)
    }
}