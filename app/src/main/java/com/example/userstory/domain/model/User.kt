package com.example.userstory.domain.model

import com.google.gson.annotations.SerializedName

open class User (
    @SerializedName("login")
    var login: String? = null,

    @SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @SerializedName("html_url")
    var htmlUrl: String? = null
)