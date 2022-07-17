package com.semicolon.shakeit.feature.editprofile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semicolon.design.Body2
import com.semicolon.design.Subtitle3
import com.semicolon.design.color.primary.gray.gray300
import com.semicolon.design.color.system.red.red400
import com.semicolon.shakeit.R
import com.semicolon.shakeit.component.BigPrimaryButton
import com.semicolon.shakeit.component.CircularImage
import com.semicolon.shakeit.component.TextField
import com.semicolon.shakeit.util.fetchImage
import com.semicolon.shakeit.util.makeToast
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun EditProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val editProfileViewModel: EditProfileViewModel = hiltViewModel()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var curImage by remember { mutableStateOf<File?>(null) }
    var curNickname by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        editProfileViewModel.fetchMyProfile()
        editProfileViewModel.eventFlow.collect {
            when (it) {
                is EditProfileViewModel.Event.FetchProfileEvent.Success -> {
                    curImage = it.image
                    curNickname = it.nickname
                }
                is EditProfileViewModel.Event.FetchProfileEvent.Failure -> {
                    makeToast(context, "잠시 후 다시 시도해주세요")
                    navController.popBackStack()
                }
                is EditProfileViewModel.Event.EditProfileEvent.Success ->
                    navController.popBackStack()
                is EditProfileViewModel.Event.EditProfileEvent.WrongProfile ->
                    errorMessage = "이미지 형식을 확인해주세요"
                is EditProfileViewModel.Event.EditProfileEvent.ConflictNickname ->
                    errorMessage = "중복되는 닉네임입니다"
                is EditProfileViewModel.Event.EditProfileEvent.UnknownError ->
                    errorMessage = "잠시 후 다시 시도해주세요"
            }
        }
    }
    EditProfile(
        errorMessage = errorMessage,
        curImage = curImage,
        curNickname = curNickname,
        onCancelClick = { navController.popBackStack() },
        onCompleteClick = { image, nickname -> editProfileViewModel.editProfile(image, nickname) }
    )
}

@Composable
private fun EditProfile(
    errorMessage: String?,
    curImage: File?,
    curNickname: String?,
    onCancelClick: () -> Unit,
    onCompleteClick: (image: File, nickname: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var nicknameTextField by remember { mutableStateOf(curNickname) }
    var profileImage by remember { mutableStateOf(curImage) }
    val isEnabled = !nicknameTextField.isNullOrEmpty()
            && profileImage != null
            && (curNickname != nicknameTextField || curImage != profileImage)
    if (nicknameTextField == null && curNickname != null) nicknameTextField = curNickname
    if (profileImage == null && curImage != null) profileImage = curImage
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier
                .padding(bottom = 52.dp)
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .size(56.dp)
                    .clickable { onCancelClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(R.drawable.ic_cancel),
                    contentDescription = null,
                    Modifier.size(24.dp),
                    tint = Color.White
                )
            }
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(Modifier.size(16.dp))
                Subtitle3("프로필 설정", color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(Modifier.size(12.dp))
                Body2(
                    text = errorMessage ?: "사진과 닉네임을 설정해주세요",
                    color = if (errorMessage != null) red400 else gray300
                )
                Spacer(Modifier.size(24.dp))
                Box(
                    Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularImage(
                        image = profileImage,
                        size = 128.dp,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                fetchImage(context)?.let { profileImage = it }
                            }
                        })
                }
                Spacer(Modifier.size(24.dp))
                TextField(
                    text = nicknameTextField ?: "",
                    onTextChange = { nicknameTextField = it },
                    placeholder = "닉네임"
                )
            }
        }
        BigPrimaryButton(
            text = "완료",
            isEnabled = isEnabled
        ) {
            onCompleteClick(profileImage!!, nicknameTextField!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditProfilePreview() {
    EditProfile(
        errorMessage = null,
        curImage = null,
        curNickname = "신희원",
        onCancelClick = { },
        onCompleteClick = { _, _ -> }
    )
}
