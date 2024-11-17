package com.example.userstory.domain.model

import com.google.gson.annotations.SerializedName

class UserDetail(
    @SerializedName("location")
    var location: String? = null,

    @SerializedName("followers")
    var followers: Int = 0,

    @SerializedName("following")
    var following: Int = 0
) : UserInfo()