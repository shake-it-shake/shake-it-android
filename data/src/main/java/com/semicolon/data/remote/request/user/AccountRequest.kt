package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName

data class AccountRequest(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
)