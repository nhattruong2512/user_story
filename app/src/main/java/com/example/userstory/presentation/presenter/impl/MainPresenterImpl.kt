package com.example.userstory.presentation.presenter.impl

import com.example.userstory.domain.usecase.MainUseCase
import com.example.userstory.presentation.presenter.MainPresenter
import com.example.userstory.presentation.ui.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val useCase: MainUseCase
): MainPresenter {

    companion object {
        const val ITEM_PER_PAGE = 20
    }

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

    override fun loadMore(totalItems: Int) {
        scope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    val page = totalItems / ITEM_PER_PAGE + 1
                    useCase.getUsers(ITEM_PER_PAGE, page * ITEM_PER_PAGE)
                }
                view.onLoadMoreUserSuccess(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}