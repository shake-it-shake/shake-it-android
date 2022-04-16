package com.semicolon.data.local.preference

import android.content.SharedPreferences
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthPreference {

    override fun saveAccessToken(accessToken: String) =
        saveStringPreference(ACCESS_TOKEN, accessToken)

    override fun fetchAccessToken(): String =
        fetchStringPreference(ACCESS_TOKEN)

    override fun clearAccessToken() =
        clearPreference(ACCESS_TOKEN)

    override fun saveRefreshToken(refreshToken: String) =
        saveStringPreference(REFRESH_TOKEN, refreshToken)

    override fun fetchRefreshToken(): String =
        fetchStringPreference(REFRESH_TOKEN)

    override fun clearRefreshToken() =
        clearPreference(REFRESH_TOKEN)

    override fun saveExpiredAt(expiredAt: LocalDateTime) =
        saveStringPreference(EXPIRED_AT, expiredAt.toString())

    override fun fetchExpiredAt(): LocalDateTime =
        LocalDateTime.parse(fetchStringPreference(EXPIRED_AT))

    override fun clearExpiredAt() =
        clearPreference(EXPIRED_AT)

    override fun saveId(id: String) =
        saveStringPreference(ID, id)

    override fun fetchId(): String =
        fetchStringPreference(ID)

    override fun clearId() =
        clearPreference(ID)

    override fun savePassword(password: String) =
        saveStringPreference(PASSWORD, password)

    override fun fetchPassword(): String =
        fetchStringPreference(PASSWORD)

    override fun clearPassword() =
        clearPreference(PASSWORD)

    private fun fetchStringPreference(key: String): String =
        sharedPreferences.getString(key, null) ?: ""

    private fun saveStringPreference(key: String, value: String) =
        editPreference { it.putString(key, value) }

    private fun clearPreference(key: String) =
        editPreference { it.remove(key) }

    private fun editPreference(edit: (SharedPreferences.Editor) -> Unit) =
        sharedPreferences.edit().let {
            edit(it)
            it.apply()
        }

    companion object Key {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
        const val EXPIRED_AT = "EXPIRED_AT"
        const val ID = "ID"
        const val PASSWORD = "PASSWORD"
    }
}