package com.semicolon.data.local.preference

import org.threeten.bp.LocalDateTime

interface AuthPreference {

    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String

    fun clearAccessToken()

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String

    fun clearRefreshToken()

    fun saveExpiredAt(expiredAt: LocalDateTime)

    fun fetchExpiredAt(): LocalDateTime

    fun clearExpiredAt()

    fun saveId(id: String)

    fun fetchId(): String

    fun clearId()

    fun savePassword(password: String)

    fun fetchPassword(): String

    fun clearPassword()
}