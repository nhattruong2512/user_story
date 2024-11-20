package com.example.userstory.data.datasource.local

import com.example.userstory.domain.model.UserDetail

internal fun UserDetail.toEntity(): UserEntity {
    return UserEntity(
        login = login ?: "",
        avatarUrl = avatarUrl ?: "",
        htmlUrl = htmlUrl ?: "",
        location = location ?: "",
        followers = followers,
        following = following
    )
}

internal fun UserEntity.toUserDetail(): UserDetail {
    return UserDetail(
        location = location,
        followers = followers,
        following = following
    ).also {
        it.login = login
        it.avatarUrl = avatarUrl
        it.htmlUrl = htmlUrl
    }
}