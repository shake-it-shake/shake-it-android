package com.semicolon.domain.repository

import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.param.friend.FriendParam

interface FriendRepository {

    suspend fun sendFriendRequest(friendParam: FriendParam)

    suspend fun acceptFriendRequest(friendParam: FriendParam)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(friendParam: FriendParam)
}