package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.LoginParam
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun login(loginParam: LoginParam) =
        userRepository.login(loginParam)
}