package com.semicolon.domain.usecase.room

import com.semicolon.domain.param.room.RoomParam
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute(roomParam: RoomParam) =
        roomRepository.createRoom(roomParam)
}