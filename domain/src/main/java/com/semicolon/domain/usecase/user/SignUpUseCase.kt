package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.SignUpParam
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(signUpParam: SignUpParam) =
        userRepository.signUp(signUpParam)
}