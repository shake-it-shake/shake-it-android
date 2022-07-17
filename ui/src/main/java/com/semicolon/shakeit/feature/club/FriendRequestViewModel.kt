package com.semicolon.shakeit.feature.club

import com.semicolon.domain.entity.friend.AddFriendEntity
import com.semicolon.domain.usecase.friend.AddFriendUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendRequestViewModel @Inject constructor(
    private val addFriendUseCase: AddFriendUseCase
) : BaseViewModel<FriendRequestViewModel.Event>() {

    fun addFriend(userId: String) = execute(
        job = { addFriendUseCase.execute(AddFriendEntity(userId)) },
        onSuccess = { emitEvent(Event.Success) },
        onFailure = { emitEvent(Event.Failure) }
    )

    sealed class Event {
        object Success : Event()
        object Failure : Event()
    }
}