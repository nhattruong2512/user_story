package com.example.userstory.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userstory.R
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.presentation.ui.view.UserDetailView

class UserDetailActivity: AppCompatActivity(), UserDetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onGetUserDetailSuccess(userDetail: UserDetail) {
    }

    override fun onGetUserDetailError(error: Throwable) {
    }
}