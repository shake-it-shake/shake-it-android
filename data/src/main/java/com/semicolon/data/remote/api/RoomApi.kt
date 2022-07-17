package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.room.CreateRoomRequest
import com.semicolon.data.remote.response.room.ClubResponse
import com.semicolon.data.remote.response.room.RoomsResponse
import retrofit2.http.*

interface RoomApi {

    @POST("rooms")
    suspend fun createRoom(
        @Body createRoomRequest: CreateRoomRequest
    ): ClubResponse

    @GET("rooms")
    suspend fun fetchRooms(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): RoomsResponse

    @POST("rooms/attendance")
    suspend fun joinRoom(
        @Query("roomId") roomId: String
    ): ClubResponse

    @DELETE("rooms")
    suspend fun leaveRoom()
}