package com.example.userstory.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.usecase.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {
    
    companion object {
        private const val TAG = "UserDetailViewModel"
    }

    private val _userDetail = MutableStateFlow<UserDetail?>(null)
    val userDetail: StateFlow<UserDetail?> = _userDetail

    fun getUserDetail(loginName: String) {
        viewModelScope.launch {
            getUserDetailUseCase(loginName)
                .catch { e ->
                    Log.e(TAG, "Get user detail error", e)
                }
                .collect { user ->
                    _userDetail.value = user
                }
        }
    }
}