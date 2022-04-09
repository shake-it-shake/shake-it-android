package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.AddFriendEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(addFriendEntity: AddFriendEntity) =
        friendRepository.addFriend(addFriendEntity)
}