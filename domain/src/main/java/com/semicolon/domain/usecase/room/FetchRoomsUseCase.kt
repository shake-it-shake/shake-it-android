package com.semicolon.domain.usecase.room

import com.semicolon.domain.param.room.FetchRoomParam
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class FetchRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend fun execute(fetchRoomParam: FetchRoomParam) =
        roomRepository.fetchRooms(fetchRoomParam)
}