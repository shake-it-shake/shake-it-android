package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.room.CreateRoomRequest
import com.semicolon.data.remote.response.room.RoomsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RoomApi {

    @POST("rooms")
    fun createRoom(
        @Body createRoomRequest: CreateRoomRequest
    )

    @GET("rooms")
    fun fetchRooms(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): RoomsResponse
}