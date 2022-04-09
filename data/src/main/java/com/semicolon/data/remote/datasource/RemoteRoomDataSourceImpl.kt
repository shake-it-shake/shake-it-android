package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ImageApi
import com.semicolon.data.remote.api.RoomApi
import com.semicolon.data.remote.request.room.toRequest
import com.semicolon.data.remote.response.room.toEntity
import com.semicolon.data.toMultipart
import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.RoomPageEntity
import com.semicolon.domain.entity.room.RoomsEntity
import javax.inject.Inject

class RemoteRoomDataSourceImpl @Inject constructor(
    private val roomApi: RoomApi,
    private val imageApi: ImageApi
) : RemoteRoomDataSource {

    override suspend fun createRoom(createRoomEntity: CreateRoomEntity) {
        val imagePath = imageApi.sendImage(createRoomEntity.image.toMultipart()).link
        roomApi.createRoom(createRoomEntity.toRequest(imagePath))
    }

    override suspend fun fetchRooms(roomPage: RoomPageEntity): RoomsEntity =
        roomApi.fetchRooms(page = roomPage.page, size = roomPage.size).toEntity()
}