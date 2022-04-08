package com.semicolon.domain.repository

import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.user.UserIdEntity

interface FriendRepository {

    suspend fun sendFriendRequest(userIdEntity: UserIdEntity)

    suspend fun acceptFriendRequest(userIdEntity: UserIdEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(userIdEntity: UserIdEntity)
}