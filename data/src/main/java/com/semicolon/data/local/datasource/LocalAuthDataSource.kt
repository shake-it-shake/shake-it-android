package com.semicolon.data.local.datasource

import com.semicolon.data.local.entity.TokenEntity
import com.semicolon.domain.entity.user.LoginEntity

interface LocalAuthDataSource {

    suspend fun saveToken(tokenEntity: TokenEntity)

    suspend fun fetchToken(): TokenEntity

    suspend fun saveAccount(loginEntity: LoginEntity)

    suspend fun fetchLoginEntity(): LoginEntity
}