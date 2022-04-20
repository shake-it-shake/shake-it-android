package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.image.ImageResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {

    @Multipart
    @POST("/images")
    suspend fun sendImage(
        @Part file: MultipartBody.Part
    ): ImageResponse
}