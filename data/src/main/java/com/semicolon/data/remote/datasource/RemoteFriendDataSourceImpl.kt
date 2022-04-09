package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.FriendApi
import com.semicolon.data.remote.request.friend.toRequest
import com.semicolon.data.remote.response.friend.toEntity
import com.semicolon.domain.entity.friend.AcceptFriendRequestEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.entity.friend.SendFriendRequestEntity
import javax.inject.Inject

class RemoteFriendDataSourceImpl @Inject constructor(
    private val friendApi: FriendApi
) : RemoteFriendDataSource {

    override suspend fun addFriend(sendFriendRequest: SendFriendRequestEntity) =
        friendApi.addFriend(sendFriendRequest.toRequest())

    override suspend fun acceptFriend(acceptFriendRequestEntity: AcceptFriendRequestEntity) =
        friendApi.acceptFriend(acceptFriendRequestEntity.toRequest())

    override suspend fun fetchFriends(): FriendsEntity =
        friendApi.fetchFriends().toEntity()

    override suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity) =
        friendApi.deleteFriend(deleteFriendEntity.toRequest())
}