package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.room.*

interface RemoteRoomDataSource {

    suspend fun createRoom(createRoomEntity: CreateRoomEntity): ClubEntity

    suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity

    suspend fun joinRoom(joinRoomEntity: JoinRoomEntity): ClubEntity

    suspend fun leaveRoom()
}