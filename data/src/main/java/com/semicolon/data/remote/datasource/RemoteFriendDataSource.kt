package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.friend.AcceptFriendRequestEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.SendFriendRequestEntity

interface RemoteFriendDataSource {

    suspend fun addFriend(sendFriendRequest: SendFriendRequestEntity)

    suspend fun acceptFriend(acceptFriendRequestEntity: AcceptFriendRequestEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)
}