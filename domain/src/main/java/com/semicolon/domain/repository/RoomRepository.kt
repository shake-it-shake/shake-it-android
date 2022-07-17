package com.semicolon.domain.repository

import com.semicolon.domain.entity.room.*

interface RoomRepository {

    suspend fun createRoom(createRoomEntity: CreateRoomEntity): ClubEntity

    suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity

    suspend fun joinRoom(joinRoomEntity: JoinRoomEntity): ClubEntity

    suspend fun leaveRoom()
}