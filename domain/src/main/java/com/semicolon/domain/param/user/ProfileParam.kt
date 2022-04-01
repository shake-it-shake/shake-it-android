package com.semicolon.domain.param.user

import java.io.File

data class ProfileParam(
    val image: File,
    val nickname: String
)