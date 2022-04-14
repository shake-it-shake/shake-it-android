package com.semicolon.shakeit.login

import com.semicolon.domain.entity.user.LoginEntity
import com.semicolon.domain.usecase.user.LoginUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginViewModel.Event>() {

    fun login(id: String, password: String) = execute(
        job = { loginUseCase.execute(LoginEntity(id = id, password = password)) },
        onSuccess = { emitEvent(Event.LoginSuccess) },
        onFailure = {
            emitEvent(Event.WrongAccount)
            // TODO 에러 처리코드 추가
        }
    )

    sealed class Event {
        object LoginSuccess : Event()
        object WrongAccount : Event()
        object NoInternet : Event()
        object UnknownError : Event()
    }
}