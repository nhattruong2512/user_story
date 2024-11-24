package com.example.userstory.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.userstory.data.local.toUser
import com.example.userstory.domain.model.User
import com.example.userstory.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val users: Flow<PagingData<User>> = getUsersUseCase().map {
        it.map { user ->
            user.toUser()
        }
    }.cachedIn(viewModelScope)
}