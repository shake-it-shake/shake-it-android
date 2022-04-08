package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ImageApi
import com.semicolon.data.remote.entity.ImageEntity
import com.semicolon.data.remote.entity.ImageUrlEntity
import com.semicolon.data.remote.entity.toMultipart
import com.semicolon.data.remote.response.image.toEntity
import javax.inject.Inject

class RemoteImageDataSourceImpl @Inject constructor(
    private val imageApi: ImageApi
) : RemoteImageDataSource {

    override suspend fun sendImage(imageEntity: ImageEntity): ImageUrlEntity =
        imageApi.sendImage(imageEntity.toMultipart()).toEntity()
}