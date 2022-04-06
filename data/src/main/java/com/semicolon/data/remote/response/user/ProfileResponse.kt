package com.semicolon.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("id") val id: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("image_path") val imagePath: String,
    @SerializedName("room_id") val roomId: Long?
)