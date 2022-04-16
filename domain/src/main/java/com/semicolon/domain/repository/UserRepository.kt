package com.semicolon.domain.repository

import com.semicolon.domain.entity.user.*

interface UserRepository {

    suspend fun autoLogin()

    suspend fun login(loginEntity: LoginEntity)

    suspend fun logout()

    suspend fun signUp(signUpEntity: SignUpEntity)

    suspend fun removeAccount()

    suspend fun editProfile(editProfileEntity: EditProfileEntity)

    suspend fun fetchProfile(fetchProfileEntity: FetchProfileEntity): ProfileEntity

    suspend fun fetchMyProfile(): ProfileEntity
}