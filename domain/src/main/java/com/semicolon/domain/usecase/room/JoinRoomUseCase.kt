package com.semicolon.domain.usecase.room

import com.semicolon.domain.entity.room.JoinRoomEntity
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class JoinRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute(joinRoomEntity: JoinRoomEntity) =
        roomRepository.joinRoom(joinRoomEntity)
}