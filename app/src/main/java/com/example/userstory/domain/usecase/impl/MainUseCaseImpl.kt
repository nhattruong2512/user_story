package com.example.userstory.domain.usecase.impl

import com.example.userstory.domain.model.UserInfo
import com.example.userstory.domain.repository.RestRepository
import com.example.userstory.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val restRepository: RestRepository
): MainUseCase {
    override suspend fun getUsers(
        perPage: Int,
        since: Int
    ): Flow<List<UserInfo>> {

    }

}