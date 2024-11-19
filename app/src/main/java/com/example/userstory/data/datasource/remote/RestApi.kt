package com.example.userstory.data.datasource.remote

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RestApi {
    @GET("users")
    suspend fun getUsers(
        @QueryMap options: Map<String, String>
    ): Response<List<UserInfo>>

    @GET("users/{login_username}")
    suspend fun getUserDetail(
        @Path("login_username") loginUserName: String
    ): Response<UserDetail>
}