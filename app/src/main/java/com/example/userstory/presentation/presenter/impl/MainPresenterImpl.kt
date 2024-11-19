package com.example.userstory.presentation.presenter.impl

import com.example.userstory.domain.usecase.MainUseCase
import com.example.userstory.presentation.presenter.MainPresenter
import com.example.userstory.presentation.ui.view.MainView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val useCase: MainUseCase
): MainPresenter {

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private lateinit var view: MainView

    override fun setView(view: MainView) {
        this.view = view
    }

    override fun getUsers() {
        scope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    useCase.getUsers(20, 100)
                }
                view.onGetUsersSuccess(data)
            } catch (e: Exception) {
                view.onGetUsersError(e)
            }
        }
    }

    fun onDestroy() {
        scope.cancel()
    }
}