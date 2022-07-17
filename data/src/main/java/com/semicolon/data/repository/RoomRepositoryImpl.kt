package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteRoomDataSource
import com.semicolon.domain.entity.room.*
import com.semicolon.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val remoteRoomDataSource: RemoteRoomDataSource
) : RoomRepository {

    override suspend fun createRoom(createRoomEntity: CreateRoomEntity): ClubEntity =
        remoteRoomDataSource.createRoom(createRoomEntity)

    override suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity =
        remoteRoomDataSource.fetchRooms(roomPage)

    override suspend fun joinRoom(joinRoomEntity: JoinRoomEntity): ClubEntity =
        remoteRoomDataSource.joinRoom(joinRoomEntity)

    override suspend fun leaveRoom() =
        remoteRoomDataSource.leaveRoom()
}