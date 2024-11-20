package com.example.userstory.presentation.ui.view

import com.example.userstory.domain.model.UserInfo

interface MainView {
    fun onGetUsersSuccess(users: List<UserInfo>)
    fun onGetUsersError(error: Throwable)

    fun onLoadMoreUserSuccess(users: List<UserInfo>)
}