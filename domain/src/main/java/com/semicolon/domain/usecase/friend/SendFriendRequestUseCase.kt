package com.semicolon.domain.usecase.friend

import com.semicolon.domain.param.friend.FriendParam
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class SendFriendRequestUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(friendParam: FriendParam) =
        friendRepository.sendFriendRequest(friendParam)
}