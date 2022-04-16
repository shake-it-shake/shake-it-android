package com.semicolon.shakeit.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semicolon.design.Body1
import com.semicolon.design.Caption
import com.semicolon.design.Subtitle4
import com.semicolon.design.color.primary.gray.gray500
import com.semicolon.design.color.primary.gray.gray900
import com.semicolon.design.color.primary.white.white
import com.semicolon.design.color.system.red.red400
import com.semicolon.shakeit.Screen
import com.semicolon.shakeit.component.CircularImage
import com.semicolon.shakeit.component.TextMenu
import com.semicolon.shakeit.component.YesOrNoDialog
import com.semicolon.shakeit.util.makeToast

@Composable
fun ProfileScreen(mainNavController: NavController) {
    val context = LocalContext.current
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val imageUrl by remember { mutableStateOf("https://avatars.githubusercontent.com/u/67129795?v=4") }
    val nickname by remember { mutableStateOf("신희원") }
    LaunchedEffect(Unit) {
        profileViewModel.fetchMyProfile()
        profileViewModel.eventFlow.collect {
            when (it) {
                is ProfileViewModel.Event.MyProfile.Success ->
                    mainNavController.navigate(Screen.EditProfile.route)
                is ProfileViewModel.Event.MyProfile.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
                is ProfileViewModel.Event.Logout.Success ->
                    mainNavController.navigate(Screen.Login.route) { popUpTo(0) }
                is ProfileViewModel.Event.Logout.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
                is ProfileViewModel.Event.RemoveAccount.Success ->
                    mainNavController.navigate(Screen.Login.route) { popUpTo(0) }
                is ProfileViewModel.Event.RemoveAccount.Failure ->
                    makeToast(context, "잠시 후 다시 시도해주세요")
            }
        }
    }
    Profile(
        imageUrl = imageUrl,
        nickname = nickname,
        onEditProfileClick = { mainNavController.navigate(Screen.EditProfile.route) },
        onLogoutClick = { profileViewModel.logout() },
        onRemoveAccountClick = { profileViewModel.removeAccount() }
    )
}

@Composable
private fun Profile(
    imageUrl: String,
    nickname: String,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onRemoveAccountClick: () -> Unit
) {
    var dialogState by remember { mutableStateOf(DialogState.NONE) }
    Column {
        Column(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
            Subtitle4("내 프로필", color = white)
            Spacer(Modifier.size(16.dp))
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularImage(imageUrl = imageUrl, size = 128.dp)
            }
            Spacer(Modifier.size(12.dp))
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Body1(nickname, color = Color.White)
            }
            Spacer(Modifier.size(24.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(gray900)
            )
            Spacer(Modifier.size(12.dp))
            Caption("프로필", color = gray500)
            Spacer(Modifier.size(12.dp))
        }
        TextMenu(text = "프로필 수정", textColor = Color.White) { onEditProfileClick() }
        Column(Modifier.padding(horizontal = 16.dp)) {
            Spacer(Modifier.size(12.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(gray900)
            )
            Spacer(Modifier.size(12.dp))
            Caption("계정 관리", color = gray500)
            Spacer(Modifier.size(12.dp))
        }
        TextMenu(text = "로그아웃", textColor = Color.White) { dialogState = DialogState.LOGOUT }
        TextMenu(text = "회원 탈퇴", textColor = red400) { dialogState = DialogState.REMOVE_ACCOUNT }
    }
    when (dialogState) {
        DialogState.LOGOUT ->
            YesOrNoDialog(
                title = "로그아웃",
                message = "정말 로그아웃 하시겠습니까?",
                onAccept = {
                    onLogoutClick()
                    dialogState = DialogState.NONE
                },
                onDismiss = { dialogState = DialogState.NONE }
            )
        DialogState.REMOVE_ACCOUNT ->
            YesOrNoDialog(
                title = "회원 탈퇴",
                message = "정말 탈퇴 하시겠습니까?",
                onAccept = {
                    onRemoveAccountClick()
                    dialogState = DialogState.NONE
                },
                onDismiss = { dialogState = DialogState.NONE }
            )
        DialogState.NONE -> {}
    }
}

private enum class DialogState {
    LOGOUT, REMOVE_ACCOUNT, NONE
}