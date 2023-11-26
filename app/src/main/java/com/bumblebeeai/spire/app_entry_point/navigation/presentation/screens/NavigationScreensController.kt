package com.bumblebeeai.spire.app_entry_point.navigation.presentation.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumblebeeai.spire.app_entry_point.navigation.presentation.manager.NavigationScreensControllerViewModel
import com.bumblebeeai.spire.app_entry_point.navigation.presentation.manager.NavigationState
import com.bumblebeeai.spire.auth.login.presentation.screens.LoginScreen
import com.bumblebeeai.spire.common.ui.SetBarsColor
import com.bumblebeeai.spire.home.HomeScreen

@Composable
internal fun NavigationScreensController() {
    val viewModel = hiltViewModel<NavigationScreensControllerViewModel>()
    val navController = rememberNavController()

    when (val state = viewModel.isLogin.value) {
        NavigationState.Init -> {
            SplashScreen()
        }

        is NavigationState.IsLogin -> {
            val startDestination = getStartDestination(state.isLogin)

            NavHost(
                navController = navController, startDestination = startDestination
            ) {
                composable(AppNavigationRouts.HOME) {
                    SetBarsColor(
                        navigationBarColor = Color.White,
                        statusBarColor = MaterialTheme.colorScheme.background
                    )
                    AppNavigationRouts.HOME

                    HomeScreen()
                }
                composable(AppNavigationRouts.LOGIN) {
                    SetBarsColor(
                        navigationBarColor = MaterialTheme.colorScheme.primary, statusBarColor =
                        MaterialTheme.colorScheme.primary
                    )

                    LoginScreen(navController)
                }
            }
        }
    }
}

@Composable
fun getStartDestination(isLogin: Boolean): String {
    return if (isLogin) {
        AppNavigationRouts.HOME
    } else {
        AppNavigationRouts.LOGIN
    }
}
