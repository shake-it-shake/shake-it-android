package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.ProfileParam
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun editProfile(profileParam: ProfileParam) =
        userRepository.editProfile(profileParam)
}