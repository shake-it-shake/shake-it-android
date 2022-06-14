package com.semicolon.domain.entity.friend

data class FriendsEntity(
    val friends: List<FriendEntity>
) {
    data class FriendEntity(
        val id: Long,
        val userId: String,
        val nickname: String,
        val profilePath: String,
        val roomId: String?
    )
}