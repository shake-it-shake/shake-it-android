package com.semicolon.shakeit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.semicolon.shakeit.feature.clubs.ClubsScreen
import com.semicolon.shakeit.feature.login.LoginScreen
import com.semicolon.shakeit.feature.editprofile.EditProfileScreen
import com.semicolon.shakeit.feature.friends.FriendsScreen
import com.semicolon.shakeit.feature.profile.ProfileScreen
import com.semicolon.shakeit.feature.signup.SignUpScreen
import com.semicolon.shakeit.feature.splash.SplashScreen
import com.semicolon.shakeit.theme.ShakeitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ShakeitTheme { ShakeIt() } }
    }
}

@Composable
fun ShakeIt() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.EditProfile.route) {
            EditProfileScreen(navController)
        }
        composable(Screen.Main.route) {
            MainScaffold(navController)
        }
    }
}

@Composable
fun MainScaffold(
    mainNavController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = Color.Black,
        bottomBar = {
            BottomNavigation(
                Modifier.padding(horizontal = 16.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    BottomNavigationScreen.Club,
                    BottomNavigationScreen.Friend,
                    BottomNavigationScreen.Profile
                )
                items.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    val color = if (selected) Color(0xFFFF6262) else Color.White
                    BottomNavigationItem(
                        modifier = Modifier
                            .background(Color.Black),
                        icon = { Icon(screen.icon(), null, tint = color) },
                        label = { Text(screen.label, color = color) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) { popUpTo(0) }
                        }
                    )
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationScreen.Club.route
        ) {
            composable(BottomNavigationScreen.Club.route) {
                ClubsScreen(mainNavController)
            }
            composable(BottomNavigationScreen.Friend.route) {
                FriendsScreen(mainNavController)
            }
            composable(BottomNavigationScreen.Profile.route) {
                ProfileScreen(mainNavController)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")

    object Login : Screen("login")

    object SignUp : Screen("signup")

    object EditProfile : Screen("profile")

    object Main : Screen("main")
}

sealed class BottomNavigationScreen(
    val route: String,
    val icon: @Composable () -> Painter,
    val label: String
) {
    object Club : BottomNavigationScreen(
        route = "main/club",
        icon = { painterResource(R.drawable.ic_disco) },
        label = "??????"
    )

    object Friend : BottomNavigationScreen(
        route = "main/friend",
        icon = { painterResource(R.drawable.ic_friend) },
        label = "??????"
    )

    object Profile : BottomNavigationScreen(
        route = "main/profile",
        icon = { painterResource(R.drawable.ic_profile) },
        label = "?????????"
    )
}