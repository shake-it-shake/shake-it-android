package com.semicolon.data.remote.request.room

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.room.CreateRoomEntity

data class CreateRoomRequest(
    @SerializedName("title") val title: String,
    @SerializedName("personnel") val personnel: Int,
    @SerializedName("image_path") val imagePath: String
)

fun CreateRoomEntity.toRequest(imagePath: String) =
    CreateRoomRequest(
        title = title,
        personnel = personnel,
        imagePath = imagePath
    )
