package com.semicolon.domain.usecase.room

import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class LeaveRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute() =
        roomRepository.leaveRoom()
}