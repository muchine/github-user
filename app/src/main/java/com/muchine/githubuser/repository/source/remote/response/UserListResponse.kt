package com.muchine.githubuser.repository.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<UserItemResponse>
)