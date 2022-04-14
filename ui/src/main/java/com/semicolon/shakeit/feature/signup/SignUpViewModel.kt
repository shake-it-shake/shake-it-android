package com.semicolon.shakeit.feature.signup

import com.semicolon.domain.entity.user.SignUpEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.CheckPasswordException
import com.semicolon.domain.exception.ConflictException
import com.semicolon.domain.usecase.user.SignUpUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<SignUpViewModel.Event>() {

    fun signUp(id: String, password: String, passwordCheck: String) = execute(
        job = {
            if (password == passwordCheck)
                signUpUseCase.execute(SignUpEntity(id = id, password = password))
            else throw CheckPasswordException()
        },
        onSuccess = { emitEvent(Event.SignUpSuccess) },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.WrongAccount)
                is ConflictException -> emitEvent(Event.ExistAccount)
                is CheckPasswordException -> emitEvent(Event.CheckPassword)
                else -> emitEvent(Event.UnknownError)
            }
        }
    )

    sealed class Event {
        object SignUpSuccess : Event()
        object WrongAccount : Event()
        object ExistAccount : Event()
        object CheckPassword : Event()
        object UnknownError : Event()
    }
}