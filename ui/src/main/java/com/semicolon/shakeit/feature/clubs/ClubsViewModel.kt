package com.semicolon.shakeit.feature.clubs

import androidx.paging.PagingData
import com.semicolon.domain.entity.room.CreateRoomEntity
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.ConflictException
import com.semicolon.domain.usecase.room.CreateRoomUseCase
import com.semicolon.shakeit.base.BaseViewModel
import com.semicolon.shakeit.util.ClubPagingSource
import com.semicolon.shakeit.util.fetchRoomsByPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ClubsViewModel @Inject constructor(
    private val clubPagingSource: ClubPagingSource,
    private val createRoomUseCase: CreateRoomUseCase
) : BaseViewModel<ClubsViewModel.Event>() {

    fun fetchRooms() = execute(
        job = { fetchRoomsByPaging(15, clubPagingSource) },
        onSuccess = { emitEvent(Event.FetchRoom.Success(it)) },
        onFailure = { emitEvent(Event.FetchRoom.Failure) },
    )

    fun createRoom(image: File, title: String, personnel: Int) = execute(
        job = { createRoomUseCase.execute(CreateRoomEntity(image, title, personnel)) },
        onSuccess = { emitEvent(Event.CreateRoom.Success) },
        onFailure = {
            emitEvent(
                when (it) {
                    is BadRequestException -> Event.CreateRoom.BadRequest
                    is ConflictException -> Event.CreateRoom.ConflictTitle
                    else -> Event.CreateRoom.UnknownError
                }
            )
        }
    )

    sealed class Event {
        sealed class FetchRoom : Event() {
            data class Success(val rooms: Flow<PagingData<RoomsEntity.RoomEntity>>) : FetchRoom()
            object Failure : FetchRoom()
        }

        sealed class CreateRoom : Event() {
            object Success : CreateRoom()
            object BadRequest : CreateRoom()
            object ConflictTitle : CreateRoom()
            object UnknownError : CreateRoom()
        }
    }
}