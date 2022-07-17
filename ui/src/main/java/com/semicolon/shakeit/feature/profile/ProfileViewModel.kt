package com.semicolon.shakeit.feature.profile

import com.semicolon.domain.usecase.user.FetchMyProfileUseCase
import com.semicolon.domain.usecase.user.LogoutUseCase
import com.semicolon.domain.usecase.user.RemoveAccountUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchMyProfileUseCase: FetchMyProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val removeAccountUseCase: RemoveAccountUseCase
) : BaseViewModel<ProfileViewModel.Event>() {

    fun fetchMyProfile() = execute(
        job = { fetchMyProfileUseCase.execute() },
        onSuccess = { emitEvent(Event.MyProfile.Success(it.imagePath, it.nickname)) },
        onFailure = {
            it.printStackTrace()
            emitEvent(Event.MyProfile.Failure)
        }
    )

    fun logout() = execute(
        job = { logoutUseCase.execute() },
        onSuccess = { emitEvent(Event.Logout.Success) },
        onFailure = { emitEvent(Event.Logout.Failure) }
    )

    fun removeAccount() = execute(
        job = { removeAccountUseCase.execute() },
        onSuccess = { emitEvent(Event.RemoveAccount.Success) },
        onFailure = { emitEvent(Event.RemoveAccount.Failure) }
    )

    sealed class Event {
        sealed class MyProfile : Event() {
            data class Success(val imageUrl: String, val nickname: String) : MyProfile()
            object Failure : MyProfile()
        }

        sealed class Logout : Event() {
            object Success : Logout()
            object Failure : Logout()
        }

        sealed class RemoveAccount : Event() {
            object Success : RemoveAccount()
            object Failure : RemoveAccount()
        }
    }
}