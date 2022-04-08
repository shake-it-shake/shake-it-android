package com.semicolon.domain.repository

import com.semicolon.domain.entity.user.AccountEntity
import com.semicolon.domain.entity.user.EditProfileEntity
import com.semicolon.domain.entity.user.ProfileEntity
import com.semicolon.domain.entity.user.UserIdEntity

interface UserRepository {

    suspend fun autoLogin()

    suspend fun login(accountEntity: AccountEntity)

    suspend fun signUp(accountEntity: AccountEntity)

    suspend fun removeAccount()

    suspend fun editProfile(editProfileEntity: EditProfileEntity)

    suspend fun fetchProfile(userIdEntity: UserIdEntity): ProfileEntity

    suspend fun fetchMyProfile(): ProfileEntity
}