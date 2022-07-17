package com.semicolon.shakeit.feature.friends

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semicolon.design.Body3
import com.semicolon.design.Subtitle4
import com.semicolon.design.color.primary.white.white
import com.semicolon.domain.entity.friend.FriendRequestsEntity
import com.semicolon.domain.entity.friend.FriendsEntity
import com.semicolon.shakeit.component.CircularImage
import com.semicolon.shakeit.component.SmallPrimaryButton
import com.semicolon.shakeit.feature.clubs.startClubActivity
import com.semicolon.shakeit.util.makeToast

@Composable
fun FriendsScreen(mainNavController: NavController) {
    val context = LocalContext.current
    val friendsViewModel: FriendsViewModel = hiltViewModel()
    var requests: List<FriendRequestsEntity.FriendRequestEntity> by remember { mutableStateOf(listOf()) }
    var friends: List<FriendsEntity.FriendEntity> by remember { mutableStateOf(listOf()) }
    LaunchedEffect(Unit) {
        friendsViewModel.fetchFriendRequests()
        friendsViewModel.fetchFriends()
        friendsViewModel.eventFlow.collect {
            when (it) {
                is FriendsViewModel.Event.AcceptFriend.Success -> {
                    friendsViewModel.fetchFriendRequests()
                    friendsViewModel.fetchFriends()
                }
                is FriendsViewModel.Event.AcceptFriend.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
                is FriendsViewModel.Event.DeleteFriend.Success -> friendsViewModel.fetchFriends()
                is FriendsViewModel.Event.DeleteFriend.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
                is FriendsViewModel.Event.FetchFriendRequests.Success -> requests = it.requests
                is FriendsViewModel.Event.FetchFriendRequests.Failure -> {}
                is FriendsViewModel.Event.FetchFriends.Success -> friends = it.friends
                is FriendsViewModel.Event.FetchFriends.Failure -> {}
            }
        }
    }
    Friends(
        requests = requests,
        friends = friends,
        onAccept = { friendsViewModel.acceptFriend(it) },
        onDelete = { friendsViewModel.deleteFriend(it) },
        onFollow = {
            if (it.roomId != null && it.roomTitle != null)
                startClubActivity(
                    context = context,
                    roomId = it.roomId!!,
                    roomTitle = it.roomTitle!!
                )
        }
    )
}

@Composable
private fun Friends(
    requests: List<FriendRequestsEntity.FriendRequestEntity>,
    friends: List<FriendsEntity.FriendEntity>,
    onAccept: (String) -> Unit,
    onDelete: (String) -> Unit,
    onFollow: (FriendsEntity.FriendEntity) -> Unit
) {
    Column {
        if (requests.isNotEmpty()) {
            Box(Modifier.padding(16.dp)) { Subtitle4("친구요청 목록", color = white) }
            LazyColumn {
                items(requests.count()) {
                    FriendRequestItem(requests[it], onAccept)
                }
            }
        }
        Box(Modifier.padding(16.dp)) { Subtitle4("친구 목록", color = white) }
        LazyColumn {
            items(friends.count()) {
                FriendItem(friends[it], onDelete, onFollow)
            }
        }
    }
}


@Composable
private fun FriendRequestItem(
    request: FriendRequestsEntity.FriendRequestEntity,
    onAccept: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircularImage(imageUrl = request.imagePath, size = 32.dp)
            Spacer(Modifier.size(8.dp))
            Body3(text = request.nickname, color = Color.White)
        }
        SmallPrimaryButton(text = "수락") {
            onAccept(request.userId)
        }
    }
}

@Composable
private fun FriendItem(
    friend: FriendsEntity.FriendEntity,
    onDelete: (String) -> Unit,
    onFollow: (FriendsEntity.FriendEntity) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { onDelete(friend.userId) })
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircularImage(imageUrl = friend.profilePath, size = 32.dp)
            Spacer(Modifier.size(8.dp))
            Body3(text = friend.nickname, color = Color.White)
        }
        if (friend.roomId != null) SmallPrimaryButton(
            text = "따라가기",
            isEnabled = (friend.roomId != null)
        ) {
            onFollow(friend)
        }
    }
}