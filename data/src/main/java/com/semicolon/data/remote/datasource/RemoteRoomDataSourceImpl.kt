package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ImageApi
import com.semicolon.data.remote.api.RoomApi
import com.semicolon.data.remote.request.room.toRequest
import com.semicolon.data.remote.response.room.toEntity
import com.semicolon.data.toMultipart
import com.semicolon.domain.entity.room.*
import javax.inject.Inject

class RemoteRoomDataSourceImpl @Inject constructor(
    private val roomApi: RoomApi,
    private val imageApi: ImageApi
) : RemoteRoomDataSource {

    override suspend fun createRoom(createRoomEntity: CreateRoomEntity): ClubEntity {
        val imagePath = imageApi.sendImage(createRoomEntity.image.toMultipart()).link
        return roomApi.createRoom(createRoomEntity.toRequest(imagePath)).toEntity()
    }

    override suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity =
        roomApi.fetchRooms(page = roomPage.page, size = roomPage.size).toEntity()

    override suspend fun joinRoom(joinRoomEntity: JoinRoomEntity): ClubEntity =
        roomApi.joinRoom(joinRoomEntity.roomId).toEntity()

    override suspend fun leaveRoom() =
        roomApi.leaveRoom()
}