package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.user.UserIdEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(userIdEntity: UserIdEntity) =
        friendRepository.sendFriendRequest(userIdEntity)
}