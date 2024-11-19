package com.example.userstory.data.datasource.local

import com.example.userstory.domain.model.UserDetail
import kotlin.math.log

internal fun UserDetail.toEntity(): UserEntity {
    return UserEntity(
        id = login ?: "",
        avatarUrl = avatarUrl ?: "",
        htmlUrl = htmlUrl ?: "",
        location = location ?: "",
        followers = followers,
        following = following
    )
}

internal fun UserEntity.toUserDetail(): UserDetail {
    return UserDetail(
        location = location ?: "",
        followers = followers,
        following = following
    ).apply {
        login = id ?: ""
        avatarUrl = avatarUrl ?: ""
        htmlUrl = htmlUrl ?: ""
    }
}