package com.example.userstory.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.userstory.databinding.ActivityUserDetailBinding
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.presentation.presenter.UserDetailPresenter
import com.example.userstory.presentation.ui.view.UserDetailView
import com.example.userstory.presentation.utils.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailActivity: AppCompatActivity(), UserDetailView {

    companion object {
        private const val LOGIN_NAME = "login_name"

        fun newIntent(context: Context?, loginName: String) =
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(LOGIN_NAME, loginName)
            }
    }

    @Inject
    lateinit var presenter: UserDetailPresenter

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.setView(this)
        intent.getStringExtra(LOGIN_NAME)?.let { loginName ->
            presenter.getUserDetail(loginName)
        } ?: {
            Toast.makeText(
                this@UserDetailActivity,
                "login name is null",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }

        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onGetUserDetailSuccess(userDetail: UserDetail) {
        with(binding) {
            ImageLoader.loadAvatar(
                requestManager =  Glide.with(this@UserDetailActivity),
                url = userDetail.avatarUrl,
                target = ivUserAvatar
            )

            tvUserName.text = userDetail.login
            tvLocation.text = userDetail.location
            tvFollowerCount.text = userDetail.followers.toString()
            tvFollowingCount.text = userDetail.following.toString()
            tvBlogLink.text = userDetail.htmlUrl
            tvBlogLink.isClickable = true
            tvBlogLink.movementMethod = LinkMovementMethod.getInstance()
            val htmlUrl = userDetail.htmlUrl
            val text = "<a href='${htmlUrl}'> $htmlUrl </a>"
            tvBlogLink.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        }
    }

    override fun onGetUserDetailError(error: Throwable) {
        Toast.makeText(this@UserDetailActivity, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}