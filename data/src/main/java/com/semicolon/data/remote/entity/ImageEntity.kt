package com.semicolon.data.remote.entity

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@JvmInline
value class ImageEntity(val image: File)

fun ImageEntity.toMultipart(): MultipartBody.Part {
    val fileBody = RequestBody.create(MediaType.parse("image/jpeg"), this.image)
    return MultipartBody.Part.createFormData("files", this.image.name, fileBody)
}