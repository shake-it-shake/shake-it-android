package com.semicolon.domain.usecase.user

import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class AutoLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute() =
        userRepository.autoLogin()
}