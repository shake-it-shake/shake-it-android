package com.semicolon.data.interceptor

import com.google.gson.Gson
import com.semicolon.data.local.preference.AuthPreference
import com.semicolon.data.remote.response.user.TokenResponse
import com.semicolon.domain.exception.NeedLoginException
import okhttp3.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authPreference: AuthPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/users/auth",
            "/users/registry"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)
        checkExpired()
        val accessToken = authPreference.fetchAccessToken()
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }

    private fun checkExpired() {
        val expiredAt = authPreference.fetchExpiredAt()
        val curDateTime = LocalDateTime.now(ZoneId.systemDefault())
        if (expiredAt.isBefore(curDateTime)) refreshToken()

    }

    private fun refreshToken() {
        val client = OkHttpClient()
        val refreshToken = authPreference.fetchRefreshToken()
        val tokenRefreshRequest = Request.Builder()
            .url("http://13.124.183.39:3000/users/token")
            .patch(RequestBody.create(MediaType.parse("application/json"), ""))
            .addHeader("Refresh-Token", "Bearer $refreshToken")
            .build()
        val response = client.newCall(tokenRefreshRequest).execute()

        if (response.isSuccessful) {
            val token = Gson().fromJson(
                response.body()!!.toString(),
                TokenResponse::class.java
            )
            authPreference.saveAccessToken(token.accessToken)
            authPreference.saveRefreshToken(token.refreshToken)
            authPreference.saveExpiredAt(LocalDateTime.parse(token.expiredAt))
        } else throw NeedLoginException()
    }
}