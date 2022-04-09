package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.AddFriendEntity

interface RemoteFriendDataSource {

    suspend fun addFriend(addFriendRequest: AddFriendEntity)

    suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)
}