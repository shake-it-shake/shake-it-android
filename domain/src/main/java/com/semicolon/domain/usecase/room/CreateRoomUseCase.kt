package com.semicolon.domain.usecase.room

import com.semicolon.domain.entity.room.ClubEntity
import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute(createRoomEntity: CreateRoomEntity) =
        roomRepository.createRoom(createRoomEntity)
}