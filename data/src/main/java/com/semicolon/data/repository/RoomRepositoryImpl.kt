package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteRoomDataSource
import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val remoteRoomDataSource: RemoteRoomDataSource
) : RoomRepository {

    override suspend fun createRoom(createRoomEntity: CreateRoomEntity) =
        remoteRoomDataSource.createRoom(createRoomEntity)

    override suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity =
        remoteRoomDataSource.fetchRooms(roomPage)
}