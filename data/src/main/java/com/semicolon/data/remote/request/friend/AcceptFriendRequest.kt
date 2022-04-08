package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName

@JvmInline
value class AcceptFriendRequest(@SerializedName("user_id") val userId: String)