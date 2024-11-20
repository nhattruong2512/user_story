package com.example.userstory.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.userstory.R

object ImageLoader {

    fun loadAvatar(
        requestManager  : RequestManager,
        url             : String?,
        target          : ImageView
    ) {
        requestManager
            .load(url)
            .placeholder(R.drawable.ic_default_avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(target)
    }
}