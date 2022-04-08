package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.AcceptFriendRequestEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class AcceptFriendRequestUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(acceptFriendRequestEntity: AcceptFriendRequestEntity) =
        friendRepository.acceptFriendRequest(acceptFriendRequestEntity)
}