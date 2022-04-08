package com.semicolon.data.local.preference

import android.content.SharedPreferences
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthPreference {

    override fun saveAccessToken(accessToken: String) =
        sharedPreferences.edit().let {
            it.putString(ACCESS_TOKEN, accessToken)
            it.apply()
        }

    override fun fetchAccessToken(): String =
        sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""

    override fun saveRefreshToken(refreshToken: String) =
        sharedPreferences.edit().let {
            it.putString(REFRESH_TOKEN, refreshToken)
            it.apply()
        }

    override fun fetchRefreshToken(): String =
        sharedPreferences.getString(REFRESH_TOKEN, "") ?: ""

    override fun setExpiredAt(expiredAt: LocalDateTime) =
        sharedPreferences.edit().let {
            it.putString(EXPIRED_AT, expiredAt.toString())
            it.apply()
        }

    override fun fetchExpiredAt(): LocalDateTime =
        LocalDateTime.parse(sharedPreferences.getString(EXPIRED_AT, "") ?: "")

    override fun saveId(id: String) =
        sharedPreferences.edit().let {
            it.putString(ID, id)
            it.apply()
        }

    override fun fetchId(): String =
        sharedPreferences.getString(ID, "") ?: ""

    override fun savePassword(password: String) =
        sharedPreferences.edit().let {
            it.putString(PASSWORD, password)
            it.apply()
        }

    override fun fetchPassword(): String =
        sharedPreferences.getString(PASSWORD, "") ?: ""

    companion object Key {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val EXPIRED_AT = "EXPIRED_AT"
        const val ID = "ID"
        const val PASSWORD = "PASSWORD"
    }
}