package com.example.userstory.data.local

import com.example.userstory.domain.model.User

internal fun User.toEntity(): UserEntity {
    return UserEntity(
        login = login ?: "",
        avatarUrl = avatarUrl ?: "",
        htmlUrl = htmlUrl ?: ""
    )
}

internal fun UserEntity.toUser(): User {
    return User(
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl
    )
}