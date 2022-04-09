package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.entity.room.RoomsEntity

interface RemoteRoomDataSource {

    suspend fun createRoom(createRoomEntity: CreateRoomEntity)

    suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity
}