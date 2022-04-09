package com.semicolon.data.remote.datasource

import com.semicolon.data.local.entity.TokenEntity
import com.semicolon.domain.entity.user.*

interface RemoteUserDataSource {

    suspend fun login(loginEntity: LoginEntity): TokenEntity

    suspend fun signUp(signUpEntity: SignUpEntity): TokenEntity

    suspend fun removeAccount()

    suspend fun editProfile(editProfileEntity: EditProfileEntity)

    suspend fun fetchProfile(fetchProfileEntity: FetchProfileEntity): ProfileEntity
}