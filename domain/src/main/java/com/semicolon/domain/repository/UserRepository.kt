package com.semicolon.domain.repository

import com.semicolon.domain.entity.user.ProfileEntity
import com.semicolon.domain.param.user.FetchProfileParam
import com.semicolon.domain.param.user.LoginParam
import com.semicolon.domain.param.user.ProfileParam
import com.semicolon.domain.param.user.SignUpParam

interface UserRepository {

    suspend fun autoLogin()

    suspend fun login(loginParam: LoginParam)

    suspend fun signUp(signUpParam: SignUpParam)

    suspend fun removeAccount()

    suspend fun editProfile(profileParam: ProfileParam)

    suspend fun fetchProfile(fetchProfileParam: FetchProfileParam): ProfileEntity

    suspend fun fetchMyProfile(): ProfileEntity
}