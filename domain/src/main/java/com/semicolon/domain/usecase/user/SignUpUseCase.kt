package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.SignUpEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.math.sign

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(signUpEntity: SignUpEntity) =
        userRepository.signUp(signUpEntity)
}