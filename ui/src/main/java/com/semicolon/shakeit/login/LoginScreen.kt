package com.semicolon.shakeit.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semicolon.design.Body2
import com.semicolon.design.Body3
import com.semicolon.design.Subtitle3
import com.semicolon.design.color.primary.gray.gray300
import com.semicolon.design.color.system.red.red400
import com.semicolon.shakeit.Screen
import com.semicolon.shakeit.component.BigPrimaryButton
import com.semicolon.shakeit.component.TextField

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        loginViewModel.eventFlow.collect {
            when (it) {
                LoginViewModel.Event.LoginSuccess ->
                    navController.navigate(Screen.Main.route) { popUpTo(0) }
                LoginViewModel.Event.WrongAccount ->
                    errorMessage = "아이디와 비밀번호를 확인해주세요"
                LoginViewModel.Event.NoInternet ->
                    errorMessage = "인터넷 연결을 확인해주세요"
                LoginViewModel.Event.UnknownError ->
                    errorMessage = "잠시 후 다시 시도해주세요"
            }
        }
    }
    Login(
        errorMessage = errorMessage,
        onGoSignUpClick = { navController.navigate(Screen.SignUp.route) },
        onLoginClick = { id, password -> loginViewModel.login(id, password) }
    )
}

@Composable
private fun Login(
    errorMessage: String?,
    onGoSignUpClick: () -> Unit,
    onLoginClick: (id: String, password: String) -> Unit,
) {
    var idTextField by remember { mutableStateOf("") }
    var passwordTextField by remember { mutableStateOf("") }
    val isEnabled = idTextField.isNotEmpty() && passwordTextField.isNotEmpty()
    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 72.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier
                .padding(bottom = 52.dp)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Subtitle3("로그인하고", color = White, fontWeight = FontWeight.Bold)
            Subtitle3("흔들어보세요", color = White, fontWeight = FontWeight.Bold)
            Spacer(Modifier.size(12.dp))
            Body2(
                text = errorMessage ?: "신나게 흔들 준비 되셨나요?",
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
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Body3("계정이 없으신가요?", color = White)
                Spacer(Modifier.size(8.dp))
                Box(
                    Modifier
                        .height(42.dp)
                        .clickable { onGoSignUpClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Body3(
                        text = "회원가입 하기",
                        color = Color(0xFFFF6262),
                        modifier = Modifier
                    )
                }
            }
        }
        BigPrimaryButton(
            text = "로그인",
            isEnabled = isEnabled
        ) {
            onLoginClick(idTextField, passwordTextField)
        }
    }
}