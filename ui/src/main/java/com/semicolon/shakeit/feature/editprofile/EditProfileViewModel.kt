package com.semicolon.shakeit.feature.editprofile

import com.semicolon.domain.entity.user.EditProfileEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.ConflictException
import com.semicolon.domain.usecase.user.EditProfileUseCase
import com.semicolon.domain.usecase.user.FetchMyProfileUseCase
import com.semicolon.shakeit.base.BaseViewModel
import com.semicolon.shakeit.util.UrlConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val fetchMyProfileUseCase: FetchMyProfileUseCase,
    private val editProfileUseCase: EditProfileUseCase,
    private val urlConverter: UrlConverter
) : BaseViewModel<EditProfileViewModel.Event>() {

    fun fetchMyProfile() = execute(
        job = {
            val profile = fetchMyProfileUseCase.execute()
            Event.FetchProfileEvent.Success(
                urlConverter.convert(profile.imagePath),
                profile.nickname
            )
        },
        onSuccess = { emitEvent(it) },
        onFailure = { emitEvent(Event.FetchProfileEvent.Failure) }
    )

    fun editProfile(image: File, nickname: String) = execute(
        job = { editProfileUseCase.execute(EditProfileEntity(image, nickname)) },
        onSuccess = { emitEvent(Event.EditProfileEvent.Success) },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.EditProfileEvent.WrongProfile)
                is ConflictException -> emitEvent(Event.EditProfileEvent.ConflictNickname)
                else -> emitEvent(Event.EditProfileEvent.UnknownError)
            }
        }
    )

    sealed class Event {
        sealed class FetchProfileEvent : Event() {
            data class Success(val image: File, val nickname: String) : FetchProfileEvent()
            object Failure : FetchProfileEvent()
        }

        sealed class EditProfileEvent : Event() {
            object Success : EditProfileEvent()
            object WrongProfile : EditProfileEvent()
            object ConflictNickname : EditProfileEvent()
            object UnknownError : EditProfileEvent()
        }
    }
}