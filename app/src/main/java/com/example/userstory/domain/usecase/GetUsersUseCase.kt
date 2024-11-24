package com.example.userstory.domain.usecase

import androidx.paging.PagingData
import com.example.userstory.data.local.UserEntity
import com.example.userstory.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<PagingData<UserEntity>> {
        return repository.getUsers()
    }
}