package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.FetchProfileEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(fetchProfileEntity: FetchProfileEntity) =
        userRepository.fetchProfile(fetchProfileEntity)
}