package com.semicolon.domain.usecase.friend

import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class FetchFriendsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute() =
        friendRepository.fetchFriends()
}