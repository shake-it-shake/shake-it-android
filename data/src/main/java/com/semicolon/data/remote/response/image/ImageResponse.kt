package com.semicolon.data.remote.response.image

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("link") val link: String
)