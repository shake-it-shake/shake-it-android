package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.FriendApi
import com.semicolon.data.remote.request.friend.toRequest
import com.semicolon.data.remote.response.friend.toEntity
import com.semicolon.domain.entity.friend.*
import javax.inject.Inject

class RemoteFriendDataSourceImpl @Inject constructor(
    private val friendApi: FriendApi
) : RemoteFriendDataSource {

    override suspend fun addFriend(addFriendRequest: AddFriendEntity) =
        friendApi.addFriend(addFriendRequest.toRequest())

    override suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity) =
        try { friendApi.acceptFriend(acceptFriendEntity.toRequest()) }
        catch (e: KotlinNullPointerException) {}

    override suspend fun fetchFriends(): FriendsEntity =
        friendApi.fetchFriends().toEntity()

    override suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity) =
        try { friendApi.deleteFriend(deleteFriendEntity.toRequest()) }
        catch (e: KotlinNullPointerException) {}

    override suspend fun fetchFriendRequests(): FriendRequestsEntity =
        friendApi.fetchFriendRequests().toEntity()
}