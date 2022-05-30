package com.semicolon.shakeit.feature.clubs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.semicolon.design.Subtitle4
import com.semicolon.design.color.primary.white.white
import com.semicolon.domain.entity.room.RoomsEntity
import com.semicolon.shakeit.R
import com.semicolon.shakeit.util.makeToast
import kotlinx.coroutines.flow.Flow
import java.io.File

@Composable
fun ClubsScreen(mainNavController: NavController) {
    val context = LocalContext.current
    val clubsViewModel: ClubsViewModel = hiltViewModel()
    var clubs by remember { mutableStateOf<Flow<PagingData<RoomsEntity.RoomEntity>>?>(null) }
    LaunchedEffect(Unit) {
        clubsViewModel.fetchRooms()
        clubsViewModel.eventFlow.collect {
            when (it) {
                is ClubsViewModel.Event.FetchRoom.Success -> clubs = it.rooms
                is ClubsViewModel.Event.FetchRoom.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
                is ClubsViewModel.Event.CreateRoom.Success -> {}
                is ClubsViewModel.Event.CreateRoom.BadRequest -> {}
                is ClubsViewModel.Event.CreateRoom.ConflictTitle -> {}
                is ClubsViewModel.Event.CreateRoom.UnknownError -> {}
            }
        }
    }
    Clubs(
        clubs = clubs,
        onJoinClick = { TODO() },
        onRoomCreateClick = { file, s, i -> clubsViewModel.createRoom(file, s, i) }
    )
}

@Composable
private fun Clubs(
    clubs: Flow<PagingData<RoomsEntity.RoomEntity>>?,
    onJoinClick: (RoomsEntity.RoomEntity) -> Unit,
    onRoomCreateClick: (File, String, Int) -> Unit
) {
    val clubsState = clubs?.collectAsLazyPagingItems()
    var makeClubDialogState by remember { mutableStateOf(false) }
    Column {
        Row(
            Modifier
                .height(62.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Subtitle4("클럽 목록", color = white)
            Box(
                Modifier
                    .size(56.dp)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { makeClubDialogState = true }
                )
            }
        }
        LazyColumn(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 56.dp)
        ) {
            items(clubsState?.itemCount ?: 0) {
                clubsState?.get(it)?.let { room ->
                    ClubItem(room, onJoinClick)
                    Spacer(Modifier.size(12.dp))
                }
            }
        }
    }
    if (makeClubDialogState) MakeClubDialog(
        onCreate = { image, name, maxPeople ->
            makeClubDialogState = false
            onRoomCreateClick(image, name, maxPeople)
        },
        onDismiss = {
            makeClubDialogState = false
        }
    )
}