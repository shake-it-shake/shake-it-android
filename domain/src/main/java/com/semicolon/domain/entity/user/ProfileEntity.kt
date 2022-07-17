package com.semicolon.domain.entity.user

data class ProfileEntity(
    val id: String,
    val nickname: String,
    val imagePath: String,
    val roomId: String?
)