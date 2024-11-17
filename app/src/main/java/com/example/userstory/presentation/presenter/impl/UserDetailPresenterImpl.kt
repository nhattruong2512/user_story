package com.example.userstory.presentation.presenter.impl

import com.example.userstory.domain.usecase.UserDetailUseCase
import com.example.userstory.presentation.presenter.UserDetailPresenter
import javax.inject.Inject

class UserDetailPresenterImpl @Inject constructor(
    private val useCase: UserDetailUseCase
): UserDetailPresenter {
    override fun getUserDetail(loginName: String) {
        useCase.getUserDetail(loginName,
            onSuccess = {

            },
            onError = {

            }
        )
    }
}