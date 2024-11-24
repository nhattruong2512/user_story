package com.example.userstory.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.userstory.data.local.AppDatabase
import com.example.userstory.data.local.UserEntity
import com.example.userstory.data.local.toEntity
import com.example.userstory.data.remote.BaseNetworkException
import com.example.userstory.data.remote.RestApi

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val apiService: RestApi,
    private val database: AppDatabase
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, UserEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
                    val lastPage = lastItem?.id ?: 0
                    (lastPage / state.config.pageSize) + 1
                }
            }

            val pageSize = state.config.pageSize
            val since = pageSize * page
            val response = apiService.getUsers(pageSize, since)
            val users = response.body()
            if (!users.isNullOrEmpty()){
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.userDao.clearAll()
                    }
                    database.userDao.insertAll(
                        users.map {
                            it.toEntity()
                        }
                    )
                }
            } else {
                MediatorResult.Error(BaseNetworkException(responseMessage = "Users is null or empty"))
            }
            MediatorResult.Success(endOfPaginationReached = response.body().isNullOrEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}