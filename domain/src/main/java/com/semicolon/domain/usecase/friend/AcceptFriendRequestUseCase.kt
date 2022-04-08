package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.user.UserIdEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class AcceptFriendRequestUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(userIdEntity: UserIdEntity) =
        friendRepository.acceptFriendRequest(userIdEntity)
}