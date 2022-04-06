package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.user.AccountRequest
import com.semicolon.data.remote.request.user.EditProfileRequest
import com.semicolon.data.remote.response.user.ProfileResponse
import com.semicolon.data.remote.response.user.TokenResponse
import retrofit2.http.*

interface UserApi {

    @POST("/users/auth")
    suspend fun login(
        @Body accountRequest: AccountRequest
    ): TokenResponse

    @POST("/users/registry")
    suspend fun signUp(
        @Body accountRequest: AccountRequest
    ): TokenResponse

    @DELETE("/users/leave")
    suspend fun removeAccount()

    @PATCH("/users/profile")
    suspend fun editProfile(
        @Body editProfileRequest: EditProfileRequest
    )

    @GET("/users/profile/{user_id}")
    suspend fun fetchProfile(
        @Path("user_id") userId: String
    ): ProfileResponse
}