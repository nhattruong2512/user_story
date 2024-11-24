package com.example.userstory.data.remote

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): Response<List<User>>

    @GET("users/{login_username}")
    suspend fun getUserDetail(
        @Path("login_username") loginUserName: String
    ): Response<UserDetail>
}