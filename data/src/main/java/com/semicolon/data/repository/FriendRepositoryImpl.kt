package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteFriendDataSource
import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.entity.friend.AddFriendEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendsEntity
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
}