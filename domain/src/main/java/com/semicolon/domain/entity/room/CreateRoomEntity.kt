package com.semicolon.domain.entity.room

import java.io.File

data class CreateRoomEntity(
    val image: File,
    val title: String,
    val personnel: Int
)
