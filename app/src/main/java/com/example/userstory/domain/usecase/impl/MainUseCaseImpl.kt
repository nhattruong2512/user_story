package com.example.userstory.domain.usecase.impl

import com.example.userstory.domain.model.UserInfo
import com.example.userstory.domain.repository.LocalRepository
import com.example.userstory.domain.repository.RestRepository
import com.example.userstory.domain.usecase.MainUseCase
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val restRepository: RestRepository
): MainUseCase {
    override fun getUsers(
        perPage: Int,
        since: Int,
        onSuccess: (List<UserInfo>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        restRepository.getUsers(perPage, since)
    }

}