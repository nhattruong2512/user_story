package com.example.userstory.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.userstory.databinding.ActivityUserDetailBinding
import com.example.userstory.presentation.utils.ImageLoader
import com.example.userstory.presentation.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailActivity: AppCompatActivity() {

    companion object {
        private const val LOGIN_NAME = "login_name"

        fun newIntent(context: Context?, loginName: String) =
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(LOGIN_NAME, loginName)
            }
    }

    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleIntent()
        initListener()
    }

    private fun handleIntent() {
        intent.getStringExtra(LOGIN_NAME)?.let { loginName ->
            bindViewModel(loginName)
        } ?: {
            Toast.makeText(
                this@UserDetailActivity,
                "login name is null",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    private fun initListener() {
        binding.icBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun bindViewModel(loginName: String) {
        lifecycleScope.launch {
            viewModel.userDetail
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { user ->
                    user?.let {
                        with(binding) {
                            ImageLoader.loadAvatar(
                                requestManager =  Glide.with(this@UserDetailActivity),
                                url = user.avatarUrl,
                                target = ivUserAvatar
                            )

                            tvUserName.text = user.login
                            tvLocation.text = user.location
                            tvFollowerCount.text = user.followers.toString()
                            tvFollowingCount.text = user.following.toString()
                            tvBlogLink.text = user.htmlUrl
                            tvBlogLink.isClickable = true
                            tvBlogLink.movementMethod = LinkMovementMethod.getInstance()
                            val htmlUrl = user.htmlUrl
                            val text = "<a href='${htmlUrl}'> $htmlUrl </a>"
                            tvBlogLink.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
                        }
                    }
                }
        }

        viewModel.getUserDetail(loginName)
    }
}