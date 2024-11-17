package com.example.userstory.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.userstory.R
import com.example.userstory.domain.model.UserInfo
import com.example.userstory.presentation.ui.view.MainView

class MainActivity: AppCompatActivity(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onGetUsersSuccess(users: List<UserInfo>) {

    }

    override fun onGetUsersError(error: Throwable) {
    }
}