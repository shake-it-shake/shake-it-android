package com.semicolon.data.local.entity

import java.time.LocalDateTime

data class TokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: LocalDateTime
)