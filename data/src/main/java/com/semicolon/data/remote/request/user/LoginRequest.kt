package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)