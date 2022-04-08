package com.semicolon.domain.entity.user

import java.io.File

data class EditProfileEntity(
    val image: File,
    val nickname: String
)
