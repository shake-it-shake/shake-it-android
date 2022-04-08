package com.semicolon.data.local.preference

import org.threeten.bp.LocalDateTime

interface AuthPreference {

    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String

    fun setExpiredAt(expiredAt: LocalDateTime)

    fun fetchExpiredAt(): LocalDateTime

    fun saveId(id: String)

    fun fetchId(): String

    fun savePassword(password: String)

    fun fetchPassword(): String
}