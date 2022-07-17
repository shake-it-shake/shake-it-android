package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.friend.AcceptFriendRequest
import com.semicolon.data.remote.request.friend.AddFriendRequest
import com.semicolon.data.remote.request.friend.DeleteFriendRequest
import com.semicolon.data.remote.response.friend.FriendRequestsResponse
import com.semicolon.data.remote.response.friend.FriendsResponse
import retrofit2.http.*

interface FriendApi {

    @POST("/friends")
    suspend fun addFriend(
        @Body addFriendRequest: AddFriendRequest
    )

    @PATCH("/friends")
    suspend fun acceptFriend(
        @Body acceptFriendRequest: AcceptFriendRequest
    )

    @GET("/friends")
    suspend fun fetchFriends(): FriendsResponse

    @HTTP(method = "DELETE", path = "/friends", hasBody = true)
    suspend fun deleteFriend(
        @Body deleteFriendRequest: DeleteFriendRequest
    )

    @GET("/friends/requests")
    suspend fun fetchFriendRequests(): FriendRequestsResponse
}