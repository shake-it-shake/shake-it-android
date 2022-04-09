package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.user.EditProfileEntity

data class EditProfileRequest(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image_path") val imagePath: String
)

fun EditProfileEntity.toRequest(imagePath: String) =
    EditProfileRequest(
        nickname = nickname,
        imagePath = imagePath
    )