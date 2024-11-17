package com.example.userstory.data.datasource.remote

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("users")
    fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): List<UserInfo>

    @GET("users/{login_username}")
    fun getUserDetail(
        @Path("login_username") loginUserName: String
    ): UserDetail
}