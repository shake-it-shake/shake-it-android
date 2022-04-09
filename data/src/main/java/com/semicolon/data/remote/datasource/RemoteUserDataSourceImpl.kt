package com.semicolon.data.remote.datasource

import com.semicolon.data.local.entity.TokenEntity
import com.semicolon.data.remote.api.ImageApi
import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.user.toRequest
import com.semicolon.data.remote.response.user.toEntity
import com.semicolon.data.toMultipart
import com.semicolon.domain.entity.user.*
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val imageApi: ImageApi
) : RemoteUserDataSource {

    override suspend fun login(loginEntity: LoginEntity): TokenEntity =
        userApi.login(loginEntity.toRequest()).toEntity()


    override suspend fun signUp(signUpEntity: SignUpEntity) =
        userApi.signUp(signUpEntity.toRequest()).toEntity()

    override suspend fun removeAccount() =
        userApi.removeAccount()

    override suspend fun editProfile(editProfileEntity: EditProfileEntity) {
        val imagePath = imageApi.sendImage(editProfileEntity.image.toMultipart()).link
        userApi.editProfile(editProfileEntity.toRequest(imagePath))
    }

    override suspend fun fetchProfile(fetchProfileEntity: FetchProfileEntity): ProfileEntity =
        userApi.fetchProfile(fetchProfileEntity.userId).toEntity()
}