package com.muchine.githubuser.repository.source.response

import com.google.gson.annotations.SerializedName

data class UserItemResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("login")
    val name: String
)