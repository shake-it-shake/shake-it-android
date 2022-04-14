package com.semicolon.shakeit.signup

import com.semicolon.domain.entity.user.SignUpEntity
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
            else emitEvent(Event.CheckPassword)
        },
        onSuccess = { emitEvent(Event.SignUpSuccess) },
        onFailure = {
            emitEvent(Event.ExistAccount)
            // TODO : 에러 처리코드 추가
        }
    )

    sealed class Event {
        object SignUpSuccess : Event()
        object WrongAccount : Event()
        object ExistAccount : Event()
        object CheckPassword : Event()
    }
}