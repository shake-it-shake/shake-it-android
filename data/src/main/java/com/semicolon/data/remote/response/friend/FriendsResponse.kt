package com.semicolon.data.remote.response.friend

import com.google.gson.annotations.SerializedName

data class FriendsResponse(
    @SerializedName("friends") val friends: List<Friend>
) {
    data class Friend(
        @SerializedName("id") val id: Long,
        @SerializedName("user_id") val userId: String,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("profile_path") val profilePath: String,
        @SerializedName("room_id") val roomId: Long?
    )
}