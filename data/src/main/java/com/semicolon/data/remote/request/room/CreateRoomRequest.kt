package com.semicolon.data.remote.request.room

import com.google.gson.annotations.SerializedName

data class CreateRoomRequest(
    @SerializedName("title") val title: String,
    @SerializedName("personnel") val personnel: Int,
    @SerializedName("image_path") val imagePath: String
)
