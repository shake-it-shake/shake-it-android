package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.user.LoginEntity

data class LoginRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)

fun LoginEntity.toRequest() =
    LoginRequest(
        id = id,
        password = password
    )