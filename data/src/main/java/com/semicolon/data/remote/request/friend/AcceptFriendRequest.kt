package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.AcceptFriendEntity


data class AcceptFriendRequest(
    @SerializedName("user_id") val userId: String
)

fun AcceptFriendEntity.toRequest() =
    AcceptFriendRequest(userId)