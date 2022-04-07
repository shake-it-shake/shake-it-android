package com.semicolon.data.remote.request.user

import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image_path") val imagePath: String
)