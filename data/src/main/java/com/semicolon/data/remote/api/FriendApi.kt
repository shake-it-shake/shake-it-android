package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.friend.FriendIdRequest
import com.semicolon.data.remote.response.friend.FriendsResponse
import retrofit2.http.*

interface FriendApi {

    @POST("/friends")
    suspend fun sendFriendRequest(
        @Body friendIdRequest: FriendIdRequest
    )

    @PATCH("/friends")
    suspend fun acceptFriendRequest(
        @Body friendIdRequest: FriendIdRequest
    )

    @GET("/friends")
    suspend fun fetchFriends(): FriendsResponse

    @DELETE("/friends")
    suspend fun deleteFriend(
        @Body friendIdRequest: FriendIdRequest
    )
}