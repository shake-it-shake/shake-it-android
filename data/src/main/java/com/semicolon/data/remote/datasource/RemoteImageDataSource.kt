package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.entity.ImageEntity
import com.semicolon.data.remote.entity.ImageUrlEntity

interface RemoteImageDataSource {

    suspend fun sendImage(imageEntity: ImageEntity): ImageUrlEntity
}