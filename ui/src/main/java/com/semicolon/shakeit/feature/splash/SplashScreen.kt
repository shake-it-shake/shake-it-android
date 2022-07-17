package com.semicolon.shakeit.feature.splash

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gun0912.tedpermission.coroutine.TedPermission
import com.semicolon.shakeit.R
import com.semicolon.shakeit.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {
    Splash()
    val splashViewModel: SplashViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        delay(500)
        val permissionResult = TedPermission.create()
            .setPermissions(
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
            .check()
        if (permissionResult.isGranted) splashViewModel.autoLogin()
        else navController.popBackStack()
        splashViewModel.eventFlow.collect {
            when (it) {
                SplashViewModel.Event.AutoLoginSuccess -> {
                    navController.navigate(Screen.Main.route) { popUpTo(0) }
                }
                SplashViewModel.Event.AutoLoginFailure ->
                    navController.navigate(Screen.Login.route) { popUpTo(0) }
            }
        }
    }
}

@Composable
private fun Splash() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_disco),
            contentDescription = null,
            modifier = Modifier.size(108.dp),
            tint = Color.White
        )
    }
}
