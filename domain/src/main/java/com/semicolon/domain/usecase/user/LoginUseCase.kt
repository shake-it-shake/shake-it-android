package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.AccountEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun login(accountEntity: AccountEntity) =
        userRepository.login(accountEntity)
}