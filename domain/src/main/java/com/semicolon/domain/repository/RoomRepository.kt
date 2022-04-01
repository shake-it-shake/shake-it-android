package com.semicolon.domain.repository

import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.param.room.RoomParam

interface RoomRepository {

    suspend fun createRoom(roomParam: RoomParam)

    suspend fun fetchRooms(): RoomsEntity
}