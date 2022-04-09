package com.semicolon.data.remote.response.user

import com.google.gson.annotations.SerializedName
import com.semicolon.data.local.entity.TokenEntity
import org.threeten.bp.LocalDateTime

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expired_at") val expired_at: String
)

fun TokenResponse.toEntity() =
    TokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiredAt = LocalDateTime.parse(expired_at)
    )