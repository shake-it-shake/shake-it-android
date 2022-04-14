package com.semicolon.data.remote.response.user

import com.google.gson.annotations.SerializedName
import com.semicolon.data.local.entity.TokenEntity
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expired_at") val expiredAt: String
)

fun TokenResponse.toEntity() =
    TokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiredAt = LocalDateTime.ofInstant(Instant.parse(expiredAt), ZoneId.systemDefault())
    )