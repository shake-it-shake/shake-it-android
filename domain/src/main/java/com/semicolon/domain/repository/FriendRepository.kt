package com.semicolon.domain.repository

import com.semicolon.domain.entity.friend.*

interface FriendRepository {

    suspend fun addFriend(addFriendEntity: AddFriendEntity)

    suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)

    suspend fun fetchFriendRequests(): FriendRequestsEntity
}