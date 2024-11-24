package com.example.userstory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.userstory.data.UserRemoteMediator
import com.example.userstory.data.local.AppDatabase
import com.example.userstory.data.local.UserEntity
import com.example.userstory.data.local.toUser
import com.example.userstory.data.remote.BaseNetworkException
import com.example.userstory.data.remote.NetworkErrorException
import com.example.userstory.data.remote.RestApi
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.User
import com.example.userstory.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    private val database: AppDatabase
): UserRepository{

    companion object {
        private const val PER_PAGE = 20;
    }

    private fun parseError(
        responseMessage: String?,
        responseCode: Int,
        errorBody: String?
    ): BaseNetworkException {

        val baseNetworkException = BaseNetworkException(responseMessage, responseCode)
        errorBody?.let {
            baseNetworkException.parseFromString(it)
        }

        return baseNetworkException
    }

    override fun getUsers(): Flow<PagingData<UserEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PER_PAGE
            ),
            remoteMediator = UserRemoteMediator(restApi, database),
            pagingSourceFactory = { database.userDao.getUsersPaged()}
        ).flow
    }

    override fun getUserDetail(loginName: String): Flow<UserDetail> {
        return flow {
            try {
                val response = restApi.getUserDetail(loginName)
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(body)
                    } ?: throw BaseNetworkException(
                        responseMessage = "Response without body",
                        responseCode = 200
                    )
                } else {
                    val errorBody = response.errorBody()?.string() ?: ""
                    throw parseError(response.message(), response.code(), errorBody)
                }
            } catch (e: Exception) {
                throw BaseNetworkException(responseMessage = "Unknown Error")
            }
        }
    }
}