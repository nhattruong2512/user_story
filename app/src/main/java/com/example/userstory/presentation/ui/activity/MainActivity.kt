package com.example.userstory.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userstory.databinding.ActivityMainBinding
import com.example.userstory.domain.model.User
import com.example.userstory.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.userstory.presentation.adapter.UserAdapter
import com.example.userstory.presentation.adapter.UsersLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        bindViewModel()
    }

    private fun initView() {
        binding.icBack.setOnClickListener {
            finish()
        }

        with(binding.rcvUsers){
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(
                onItemClicked = { user ->
                    openUserDetailScreen(user)
                }
            ).apply {
                this@MainActivity.userAdapter = this
            }

            binding.rcvUsers.adapter = userAdapter.withLoadStateHeaderAndFooter(
                header = UsersLoadStateAdapter { userAdapter.retry() },
                footer = UsersLoadStateAdapter { userAdapter.retry() }
            )
        }

        binding.refreshLayout.setOnRefreshListener {
            userAdapter.refresh()
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.users.collectLatest {
                userAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            userAdapter.loadStateFlow.distinctUntilChangedBy { it.source.refresh }.map { it.refresh }
                .collect { loadStates ->
                    binding.refreshLayout.isRefreshing =
                        userAdapter.itemCount != 0 && loadStates is LoadState.Loading
                }
        }
    }

    private fun openUserDetailScreen(user: User?) {
        user?.login?.let { loginName ->
            startActivity(
                UserDetailActivity.newIntent(this@MainActivity, loginName)
            )
        }
    }
}