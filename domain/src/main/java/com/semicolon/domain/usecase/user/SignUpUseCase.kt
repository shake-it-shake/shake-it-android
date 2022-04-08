package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.AccountEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(accountEntity: AccountEntity) =
        userRepository.signUp(accountEntity)
}