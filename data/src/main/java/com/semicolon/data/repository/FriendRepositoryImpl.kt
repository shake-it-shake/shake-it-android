package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteFriendDataSource
import com.semicolon.domain.entity.friend.*
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val remoteFriendDataSource: RemoteFriendDataSource
) : FriendRepository {

    override suspend fun addFriend(addFriendEntity: AddFriendEntity) =
        remoteFriendDataSource.addFriend(addFriendEntity)

    override suspend fun acceptFriend(acceptFriendEntity: AcceptFriendEntity) =
        remoteFriendDataSource.acceptFriend(acceptFriendEntity)

    override suspend fun fetchFriends(): FriendsEntity =
        remoteFriendDataSource.fetchFriends()

    override suspend fun deleteFriend(deleteFriendEntity: DeleteFriendEntity) =
        remoteFriendDataSource.deleteFriend(deleteFriendEntity)

    override suspend fun fetchFriendRequests(): FriendRequestsEntity {
        TODO("Not yet implemented")
    }
}