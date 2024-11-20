package com.example.userstory.presentation.presenter

import com.example.userstory.presentation.ui.view.UserDetailView

interface UserDetailPresenter {
    fun setView(view: UserDetailView)
    fun getUserDetail(loginName: String)
    fun onDestroy()

}