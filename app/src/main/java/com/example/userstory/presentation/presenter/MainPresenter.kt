package com.example.userstory.presentation.presenter

import com.example.userstory.presentation.ui.view.MainView

interface MainPresenter {
    fun setView(view: MainView)
    fun getUsers()
}