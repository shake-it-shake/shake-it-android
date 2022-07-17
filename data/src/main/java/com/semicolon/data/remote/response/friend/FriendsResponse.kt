package com.semicolon.data.remote.response.friend

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.friend.FriendsEntity

data class FriendsResponse(
    @SerializedName("friends") val friends: List<Friend>
) {
    data class Friend(
        @SerializedName("id") val id: Long,
        @SerializedName("user_id") val userId: String,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("profile_path") val profilePath: String,
        @SerializedName("room_id") val roomId: String?,
        @SerializedName("room_title") val roomTitle: String?
    )
}

fun FriendsResponse.toEntity() =
    FriendsEntity(
        friends = this.friends.map {
            FriendsEntity.FriendEntity(
                id = it.id,
                userId = it.userId,
                nickname = it.nickname,
                profilePath = it.profilePath,
                roomId = it.roomId,
                roomTitle = it.roomTitle
            )
        }
    )