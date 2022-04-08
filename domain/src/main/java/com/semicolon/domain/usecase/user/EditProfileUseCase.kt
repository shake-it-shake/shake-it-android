package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.user.EditProfileEntity
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(editProfileEntity: EditProfileEntity) =
        userRepository.editProfile(editProfileEntity)
}