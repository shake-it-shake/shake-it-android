package com.semicolon.shakeit.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semicolon.design.Body2
import com.semicolon.design.Subtitle3
import com.semicolon.design.color.primary.gray.gray300
import com.semicolon.design.color.system.red.red400
import com.semicolon.shakeit.R
import com.semicolon.shakeit.Screen
import com.semicolon.shakeit.component.BigPrimaryButton
import com.semicolon.shakeit.component.TextField

@Composable
fun SignUpScreen(navController: NavController) {
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        signUpViewModel.eventFlow.collect {
            when (it) {
                SignUpViewModel.Event.SignUpSuccess ->
                    navController.navigate(Screen.Main.route) { popUpTo(0) }
                SignUpViewModel.Event.WrongAccount ->
                    errorMessage = null
                SignUpViewModel.Event.ExistAccount ->
                    errorMessage = "이미 존재하는 아이디입니다"
                SignUpViewModel.Event.CheckPassword ->
                    errorMessage = "비밀번호가 일치하지 않습니다"
                SignUpViewModel.Event.UnknownError ->
                    errorMessage = "잠시 후 다시 시도해주세요"
            }
        }
    }
    SignUp(
        errorMessage = errorMessage,
        onCancelClick = { navController.popBackStack() },
        onSignUpClick = { id, password, passwordCheck ->
            signUpViewModel.signUp(id, password, passwordCheck)
        }
    )
}

@Composable
private fun SignUp(
    errorMessage: String?,
    onCancelClick: () -> Unit,
    onSignUpClick: (id: String, password: String, passwordCheck: String) -> Unit,
) {
    var idTextField by remember { mutableStateOf("") }
    var passwordTextField by remember { mutableStateOf("") }
    var passwordCheckTextField by remember { mutableStateOf("") }
    val isEnabled = idTextField.isNotEmpty()
            && passwordTextField.isNotEmpty()
            && passwordCheckTextField.isNotEmpty()
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
                .padding(horizontal = 16.dp)
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
                    Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.size(16.dp))
            Subtitle3("회원가입하고", color = Color.White, fontWeight = FontWeight.Bold)
            Subtitle3("흔들 준비 되셨나요?", color = Color.White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(12.dp))
            Body2(
                text = errorMessage ?: "아이디와 비밀번호를 입력해주세요",
                color = if (errorMessage != null) red400 else gray300
            )
            Spacer(Modifier.size(24.dp))
            TextField(
                text = idTextField,
                onTextChange = { idTextField = it },
                placeholder = "아이디"
            )
            Spacer(Modifier.size(12.dp))
            TextField(
                text = passwordTextField,
                isSecret = true,
                onTextChange = { passwordTextField = it },
                placeholder = "비밀번호"
            )
            Spacer(Modifier.size(12.dp))
            TextField(
                text = passwordCheckTextField,
                isSecret = true,
                onTextChange = { passwordCheckTextField = it },
                placeholder = "비밀번호 확인"
            )
        }
        BigPrimaryButton(
            text = "회원가입",
            isEnabled = isEnabled
        ) {
            onSignUpClick(idTextField, passwordTextField, passwordCheckTextField)
        }
    }
}