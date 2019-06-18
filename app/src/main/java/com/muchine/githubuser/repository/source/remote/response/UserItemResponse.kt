package com.muchine.githubuser.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserItemResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("login")
    val name: String
)