package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.friend.*

interface RemoteFriendDataSource {

    suspend fun addFriend(addFriendRequest: AddFriendEntity)

    suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)

    suspend fun fetchFriendRequests(): FriendRequestsEntity
}