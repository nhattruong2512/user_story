package com.example.userstory.presentation.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userstory.databinding.ActivityMainBinding
import com.example.userstory.domain.model.UserInfo
import com.example.userstory.presentation.adapter.UserAdapter
import com.example.userstory.presentation.presenter.MainPresenter
import com.example.userstory.presentation.ui.view.MainView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.setView(this)
        initView()
        initListener()
        presenter.getUsers()
    }

    private fun initListener() {
        binding.icBack.setOnClickListener {
            finish()
        }

        binding.refreshLayout.setOnRefreshListener {
            presenter.getUsers()
        }

        binding.rcvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    presenter.loadMore(totalItemCount)
                }
            }
        })
    }

    private fun initView() {
        with(binding.rcvUsers){
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter(
                onActionClick = { user ->
                    openUserDetailScreen(user)
                }
            ).apply {
                this@MainActivity.adapter = this
            }
        }
    }

    private fun openUserDetailScreen(user: UserInfo) {
        user.login?.let { loginName ->
            startActivity(
                UserDetailActivity.newIntent(this@MainActivity, loginName)
            )
        }
    }

    override fun onGetUsersSuccess(users: List<UserInfo>) {
        binding.refreshLayout.isRefreshing = false
        adapter.setData(users)
    }

    override fun onGetUsersError(error: Throwable) {
        binding.refreshLayout.isRefreshing = false
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onLoadMoreUserSuccess(users: List<UserInfo>) {
        adapter.addUsers(users)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}