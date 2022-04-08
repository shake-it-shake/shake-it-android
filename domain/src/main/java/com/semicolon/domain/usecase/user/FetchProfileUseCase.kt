package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.UserIdEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(userIdEntity: UserIdEntity) =
        userRepository.fetchProfile(userIdEntity)
}