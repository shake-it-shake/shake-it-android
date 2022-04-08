package com.semicolon.domain.repository

import com.semicolon.domain.entity.friend.AcceptFriendRequestEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.SendFriendRequestEntity

interface FriendRepository {

    suspend fun sendFriendRequest(sendFriendRequest: SendFriendRequestEntity)

    suspend fun acceptFriendRequest(acceptFriendRequestEntity: AcceptFriendRequestEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)
}