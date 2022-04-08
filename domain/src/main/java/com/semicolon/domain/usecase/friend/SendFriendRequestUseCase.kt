package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.SendFriendRequestEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(sendFriendRequestEntity: SendFriendRequestEntity) =
        friendRepository.sendFriendRequest(sendFriendRequestEntity)
}