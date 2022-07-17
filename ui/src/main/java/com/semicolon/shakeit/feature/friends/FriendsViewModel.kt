package com.semicolon.shakeit.feature.friends

import com.semicolon.domain.entity.friend.AcceptFriendEntity
import com.semicolon.domain.entity.friend.DeleteFriendEntity
import com.semicolon.domain.entity.friend.FriendRequestsEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.domain.usecase.friend.AcceptFriendUseCase
import com.semicolon.domain.usecase.friend.DeleteFriendUseCase
import com.semicolon.domain.usecase.friend.FetchFriendRequestsUseCase
import com.semicolon.domain.usecase.friend.FetchFriendsUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val acceptFriendUseCase: AcceptFriendUseCase,
    private val deleteFriendUseCase: DeleteFriendUseCase,
    private val fetchFriendRequestsUseCase: FetchFriendRequestsUseCase,
    private val fetchFriendsUseCase: FetchFriendsUseCase
) : BaseViewModel<FriendsViewModel.Event>() {

    fun acceptFriend(userId: String) = execute(
        job = { acceptFriendUseCase.execute(AcceptFriendEntity(userId)) },
        onSuccess = { emitEvent(Event.AcceptFriend.Success) },
        onFailure = { emitEvent(Event.AcceptFriend.Failure) }
    )

    fun deleteFriend(userId: String) = execute(
        job = { deleteFriendUseCase.execute(DeleteFriendEntity(userId)) },
        onSuccess = { emitEvent(Event.DeleteFriend.Success) },
        onFailure = {
            it.printStackTrace()
            emitEvent(Event.DeleteFriend.Failure)
        }
    )

    fun fetchFriendRequests() = execute(
        job = { fetchFriendRequestsUseCase.execute() },
        onSuccess = { emitEvent(Event.FetchFriendRequests.Success(it.requests)) },
        onFailure = { emitEvent(Event.FetchFriendRequests.Failure) }
    )

    fun fetchFriends() = execute(
        job = {  fetchFriendsUseCase.execute() },
        onSuccess = { emitEvent(Event.FetchFriends.Success(it.friends)) },
        onFailure = { emitEvent(Event.FetchFriends.Failure) }
    )

    sealed class Event {
        sealed class AcceptFriend : Event() {
            object Success : AcceptFriend()
            object Failure : AcceptFriend()
        }

        sealed class DeleteFriend : Event() {
            object Success : DeleteFriend()
            object Failure : DeleteFriend()
        }

        sealed class FetchFriendRequests : Event() {
            data class Success(val requests: List<FriendRequestsEntity.FriendRequestEntity>) : FetchFriendRequests()
            object Failure : FetchFriendRequests()
        }

        sealed class FetchFriends : Event() {
            data class Success(val friends: List<FriendsEntity.FriendEntity>) : FetchFriends()
            object Failure : FetchFriends()
        }
    }
}