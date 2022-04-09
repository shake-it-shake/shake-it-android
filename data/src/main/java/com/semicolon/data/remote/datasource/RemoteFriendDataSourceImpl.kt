package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.FriendApi
import com.semicolon.data.remote.request.friend.toRequest
import com.semicolon.data.remote.response.friend.toEntity
import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.AddFriendEntity
import javax.inject.Inject

class RemoteFriendDataSourceImpl @Inject constructor(
    private val friendApi: FriendApi
) : RemoteFriendDataSource {

    override suspend fun addFriend(addFriendRequest: AddFriendEntity) =
        friendApi.addFriend(addFriendRequest.toRequest())

    override suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity) =
        friendApi.acceptFriend(acceptFriendEntity.toRequest())

    override suspend fun fetchFriends(): FriendsEntity =
        friendApi.fetchFriends().toEntity()

    override suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity) =
        friendApi.deleteFriend(deleteFriendEntity.toRequest())
}