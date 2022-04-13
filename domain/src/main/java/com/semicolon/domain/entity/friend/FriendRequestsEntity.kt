package com.semicolon.domain.entity.friend

data class FriendRequestsEntity(
    val requests: List<FriendRequestEntity>
) {
    data class FriendRequestEntity(
        val userId: String,
        val nickname: String,
        val imagePath: String
    )
}