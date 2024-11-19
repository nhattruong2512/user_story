package com.example.userstory.presentation.presenter.impl

import com.example.userstory.domain.usecase.UserDetailUseCase
import com.example.userstory.presentation.presenter.UserDetailPresenter
import com.example.userstory.presentation.ui.view.UserDetailView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailPresenterImpl @Inject constructor(
    private val useCase: UserDetailUseCase
): UserDetailPresenter {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private lateinit var view: UserDetailView

    override fun setView(view: UserDetailView) {
        this.view = view
    }

    override fun getUserDetail(loginName: String) {
        scope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    useCase.getUserDetail(loginName)
                }
                view.onGetUserDetailSuccess(data)
            } catch (e: Exception) {
                view.onGetUserDetailError(e)
            }
        }
    }
}