package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.FriendRequestsEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class FetchFriendRequestsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(): FriendRequestsEntity =
        friendRepository.fetchFriendRequests()
}