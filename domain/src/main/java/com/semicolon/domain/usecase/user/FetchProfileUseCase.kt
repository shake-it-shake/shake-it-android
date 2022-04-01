package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.FetchProfileParam
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(fetchProfileParam: FetchProfileParam) =
        userRepository.fetchProfile(fetchProfileParam)
}