package com.semicolon.domain.usecase.room

import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class FetchRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute(roomPage: RoomPageEntity) =
        roomRepository.fetchRooms(roomPage)
}