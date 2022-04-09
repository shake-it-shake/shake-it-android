package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.AcceptFriendRequestEntity

@JvmInline
value class AcceptFriendRequest(@SerializedName("user_id") val userId: String)

fun AcceptFriendRequestEntity.toRequest() =
    AcceptFriendRequest(userId)