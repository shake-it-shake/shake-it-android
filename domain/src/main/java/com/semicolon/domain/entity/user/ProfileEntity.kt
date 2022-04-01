package com.semicolon.domain.entity.user

data class ProfileEntity(
    val id: Long,
    val name: String,
    val profilePath: String,
    val roomId: Long?
)