package com.semicolon.data.remote.request.friend

import com.google.gson.annotations.SerializedName

data class FriendIdRequest(
    @SerializedName("user_id") val userId: String
)