package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.LoginEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(loginEntity: LoginEntity) =
        userRepository.login(loginEntity)
}