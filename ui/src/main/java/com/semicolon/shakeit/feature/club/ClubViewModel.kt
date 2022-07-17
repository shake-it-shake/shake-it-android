package com.semicolon.shakeit.feature.club

import com.semicolon.domain.entity.room.ClubEntity
import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.JoinRoomEntity
import com.semicolon.domain.entity.user.FetchProfileEntity
import com.semicolon.domain.entity.user.ProfileEntity
import com.semicolon.domain.usecase.room.CreateRoomUseCase
import com.semicolon.domain.usecase.room.JoinRoomUseCase
import com.semicolon.domain.usecase.room.LeaveRoomUseCase
import com.semicolon.domain.usecase.user.FetchProfileUseCase
import com.semicolon.shakeit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val joinRoomUseCase: JoinRoomUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val leaveRoomUseCase: LeaveRoomUseCase,
    private val fetchProfileUseCase: FetchProfileUseCase
) : BaseViewModel<ClubViewModel.Event>() {

    fun joinRoom(roomId: String) = execute(
        job = { joinRoomUseCase.execute(JoinRoomEntity(roomId)) },
        onSuccess = { emitEvent(Event.JoinRoomEvent.Success(it)) },
        onFailure = {
            it.printStackTrace()
            emitEvent(Event.JoinRoomEvent.Failure)
        }
    )

    fun createRoom(image: File, title: String, personnel: Int) = execute(
        job = { createRoomUseCase.execute(CreateRoomEntity(image, title, personnel)) },
        onSuccess = { emitEvent(Event.CreateRoom.Success(it)) },
        onFailure = {
            it.printStackTrace()
            emitEvent(Event.JoinRoomEvent.Failure)
        }
    )

    suspend fun leaveRoom() =
        try {
            leaveRoomUseCase.execute()
        } catch (e: KotlinNullPointerException) {
        }

    fun fetchProfile(userId: String) = execute(
        job = { fetchProfileUseCase.execute(FetchProfileEntity(userId)) },
        onSuccess = { emitEvent(Event.FetchProfile.Success(it))},
        onFailure = { emitEvent(Event.FetchProfile.Failure)}
    )


    sealed class Event {
        sealed class JoinRoomEvent : Event() {
            data class Success(val clubEntity: ClubEntity) : JoinRoomEvent()
            object Failure : JoinRoomEvent()
        }

        sealed class CreateRoom : Event() {
            data class Success(val clubEntity: ClubEntity) : CreateRoom()
            object Failure : CreateRoom()
        }

        sealed class FetchProfile : Event() {
            data class Success(val profileEntity: ProfileEntity) : FetchProfile()
            object Failure : FetchProfile()
        }
    }
}