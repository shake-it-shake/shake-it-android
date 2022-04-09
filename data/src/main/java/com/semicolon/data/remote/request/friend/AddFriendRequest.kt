package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.SendFriendRequestEntity

@JvmInline
value class AddFriendRequest(@SerializedName("user_id") val userId: String)

fun SendFriendRequestEntity.toRequest() =
    AddFriendRequest(userId)