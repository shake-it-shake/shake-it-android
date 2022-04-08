package com.semicolon.domain.usecase.friend

import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.repository.FriendRepository
import javax.inject.Inject

class DeleteFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend fun execute(deleteFriendEntity: DeleteFriendEntity) =
        friendRepository.deleteFriend(deleteFriendEntity)
}