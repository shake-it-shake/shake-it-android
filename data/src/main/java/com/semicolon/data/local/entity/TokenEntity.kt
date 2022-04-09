package com.semicolon.data.local.entity

import org.threeten.bp.LocalDateTime

data class TokenEntity(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: LocalDateTime
)