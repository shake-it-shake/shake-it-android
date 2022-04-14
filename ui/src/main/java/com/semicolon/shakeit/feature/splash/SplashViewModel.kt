package com.semicolon.shakeit.feature.splash

import com.semicolon.domain.usecase.user.AutoLoginUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val autoLoginUseCase: AutoLoginUseCase
) : BaseViewModel<SplashViewModel.Event>() {

    fun autoLogin() = execute(
        job = { autoLoginUseCase.execute() },
        onSuccess = { emitEvent(Event.AutoLoginSuccess) },
        onFailure = { emitEvent(Event.AutoLoginFailure) }
    )

    sealed class Event {
        object AutoLoginSuccess : Event()
        object AutoLoginFailure : Event()
    }
}