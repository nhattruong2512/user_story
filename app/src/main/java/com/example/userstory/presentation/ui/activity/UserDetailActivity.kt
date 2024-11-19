package com.example.userstory.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userstory.R
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.presentation.presenter.UserDetailPresenter
import com.example.userstory.presentation.ui.view.UserDetailView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailActivity: AppCompatActivity(), UserDetailView {

    @Inject
    lateinit var presenter: UserDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)
    }

    override fun onGetUserDetailSuccess(userDetail: UserDetail) {
    }

    override fun onGetUserDetailError(error: Throwable) {
    }
}