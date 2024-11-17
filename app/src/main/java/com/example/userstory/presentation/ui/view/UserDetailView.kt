package com.example.userstory.presentation.ui.view

import com.example.userstory.domain.model.UserDetail

interface UserDetailView {
    fun onGetUserDetailSuccess(userDetail: UserDetail)
    fun onGetUserDetailError(error: Throwable)
}