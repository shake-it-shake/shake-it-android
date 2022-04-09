package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.user.SignUpEntity

data class SignUpRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)

fun SignUpEntity.toRequest() =
    SignUpRequest(
        id = id,
        password = password
    )