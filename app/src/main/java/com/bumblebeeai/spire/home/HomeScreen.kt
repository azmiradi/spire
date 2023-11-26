package com.bumblebeeai.spire.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.azmiradi.android_base.presentation.component.WebView
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.common.ui.CustomTopBar
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.common.ui.theme.UnSelectedItemColor
import com.bumblebeeai.spire.home.jobs.presentation.screens.JobsScreen
import com.bumblebeeai.spire.on_duty.presentation.OnDutyView

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            HomeBottomNavigation(navController)
        },
        topBar = {
            Column {
                CustomTopBar()
                Spacer(modifier = Modifier.height(14.dp))
                OnDutyView(onDutyChanged = {})
                Spacer(modifier = Modifier.height(23.dp))
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavItem.NewTasks.screenRoute,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavRouts.NEW_JOBS) {
                JobsScreen(JobType.New, navController)
            }
            composable(BottomNavRouts.MY_JOBS) {
                JobsScreen(JobType.MY, navController)
            }

            composable(BottomNavRouts.MY_ACCOUNT) {

            }
            composable(BottomNavRouts.HELP) {

            }

            composable(
                BottomNavRouts.WEB_VIEW,
//                dialogProperties = DialogProperties(
//                    usePlatformDefaultWidth = false
//                )
            ) {
                it.arguments?.getString("url")?.let {
                    WebView(it) { newUrl ->
                        if (newUrl.contains("success")) {
                            navController.popBackStack()
                        } else {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeBottomNavigation(navController: NavController) {
    var currentDestination by remember {
        mutableStateOf(BottomNavItem.NewTasks)
    }
    BottomNavigation(contentColor = Color.White, backgroundColor = Color.White) {
        BottomNavItem.values().forEach { screen ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null,
                        tint = if (currentDestination == screen) MaterialTheme.colorScheme.primary else UnSelectedItemColor
                    )
                },
                label = {
                    Text(
                        stringResource(screen.title),
                        style = CustomTypography.labelSmall.copy(
                            fontWeight = if (currentDestination == screen) FontWeight(700) else FontWeight(
                                500
                            ),
                            fontSize = 11.sp,
                        )
                    )
                },
                selected = currentDestination == screen,
                onClick = {
                    currentDestination = screen
                    navController.navigate(screen.screenRoute) {
                        popUpTo(BottomNavItem.NewTasks.screenRoute) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, unselectedContentColor = UnSelectedItemColor,
                selectedContentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}
