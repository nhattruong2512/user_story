package com.example.userstory.presentation.presenter.impl

import com.example.userstory.domain.usecase.MainUseCase
import com.example.userstory.presentation.presenter.MainPresenter
import com.example.userstory.presentation.ui.view.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
//    private val view: MainView,
    private val useCase: MainUseCase
): MainPresenter {
    override fun getUsers() {
        useCase.getUsers(20, 100,
            onSuccess = {
//                view.onGetUsersSuccess(it)
            },
            onError = {
//                view.onGetUsersError(it)
            }
        )
    }
}