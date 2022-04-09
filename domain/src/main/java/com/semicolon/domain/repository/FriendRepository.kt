package com.semicolon.domain.repository

import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.AddFriendEntity

interface FriendRepository {

    suspend fun addFriend(addFriendEntity: AddFriendEntity)

    suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity)

    suspend fun fetchFriends(): FriendsEntity

    suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity)
}