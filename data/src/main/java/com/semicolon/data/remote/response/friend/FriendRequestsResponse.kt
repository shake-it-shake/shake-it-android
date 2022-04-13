package com.semicolon.data.remote.response.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.FriendRequestsEntity

data class FriendRequestsResponse(
    @SerializedName("requests") val requests: List<FriendRequest>
) {
    data class FriendRequest(
        @SerializedName("user_id") val userId: String,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("image_path") val imagePath: String
    )
}

fun FriendRequestsResponse.toEntity() =
    FriendRequestsEntity(
        requests = requests.map {
            FriendRequestsEntity.FriendRequestEntity(
                userId = it.userId,
                nickname = it.nickname,
                imagePath = it.imagePath
            )
        }
    )