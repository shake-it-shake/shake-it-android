package com.semicolon.data.remote.response.image

import com.google.gson.annotations.SerializedName
import com.semicolon.data.remote.entity.ImageUrlEntity

data class ImageResponse(
    @SerializedName("link") val link: String
)

fun ImageResponse.toEntity() = ImageUrlEntity(link)