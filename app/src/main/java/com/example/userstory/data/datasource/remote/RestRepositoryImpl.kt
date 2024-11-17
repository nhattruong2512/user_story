package com.example.userstory.data.datasource.remote

import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo
import com.example.userstory.domain.repository.RestRepository

class RestRepositoryImpl(private val restApi: RestApi): RestRepository {

    override fun getUsers(perPage: Int, since: Int): List<UserInfo> {
        return restApi.getUsers(perPage, since)
    }


    override fun getUserDetail(loginName: String): UserDetail {
        return restApi.getUserDetail(loginName);
    }
}