package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.DeleteFriendEntity

data class DeleteFriendRequest(
    @SerializedName("user_id") val userId: String
)

fun DeleteFriendEntity.toRequest() =
    DeleteFriendRequest(userId)