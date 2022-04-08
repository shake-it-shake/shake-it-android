package com.semicolon.domain.repository

import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.entity.room.CreateRoomEntity

interface RoomRepository {

    suspend fun createRoom(createRoomEntity: CreateRoomEntity)

    suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity
}