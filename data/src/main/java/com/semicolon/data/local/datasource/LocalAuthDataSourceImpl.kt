package com.semicolon.data.local.datasource

import com.semicolon.data.local.entity.TokenEntity
import com.semicolon.data.local.preference.AuthPreference
import com.semicolon.domain.entity.user.LoginEntity
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference
) : LocalAuthDataSource {

    override suspend fun saveToken(tokenEntity: TokenEntity) =
        authPreference.let {
            it.saveAccessToken(tokenEntity.accessToken)
            it.saveRefreshToken(tokenEntity.refreshToken)
            it.saveExpiredAt(tokenEntity.expiredAt)
        }

    override suspend fun fetchToken(): TokenEntity =
        TokenEntity(
            accessToken = authPreference.fetchAccessToken(),
            refreshToken = authPreference.fetchRefreshToken(),
            expiredAt = authPreference.fetchExpiredAt()
        )

    override suspend fun saveAccount(loginEntity: LoginEntity) =
        authPreference.let {
            it.saveId(loginEntity.id)
            it.savePassword(loginEntity.password)
        }

    override suspend fun fetchAccount(): LoginEntity =
        LoginEntity(
            id = authPreference.fetchId(),
            password = authPreference.fetchPassword()
        )
}