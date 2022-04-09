package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class AcceptFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(acceptFriendEntity: AcceptFriendEntity) =
        friendRepository.acceptFriend(acceptFriendEntity)
}