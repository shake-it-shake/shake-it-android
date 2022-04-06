package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.friend.FriendIdRequest
import com.semicolon.data.remote.response.friend.FriendsResponse
import retrofit2.http.*

interface FriendApi {

    @POST("/friends")
    fun sendFriendRequest(
        @Body friendIdRequest: FriendIdRequest
    )

    @PATCH("/friends")
    fun acceptFriendRequest(
        @Body friendIdRequest: FriendIdRequest
    )

    @GET("/friends")
    fun fetchFriends(): FriendsResponse

    @DELETE("/friends")
    fun deleteFriend(
        @Body friendIdRequest: FriendIdRequest
    )
}