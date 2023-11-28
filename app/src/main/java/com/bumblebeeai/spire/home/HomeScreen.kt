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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.azmiradi.android_base.presentation.component.WebView
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.common.ui.CustomTopBar
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.common.ui.theme.UnSelectedItemColor
import com.bumblebeeai.spire.home.BottomNavRouts.JOB_DETAILS
import com.bumblebeeai.spire.home.jobs.presentation.screens.JobsScreen
import com.bumblebeeai.spire.job_details.presentation.screens.CompleteJobForm
import com.bumblebeeai.spire.job_details.presentation.screens.MapOrderDetailsScreen
import com.bumblebeeai.spire.on_duty.presentation.OnDutyView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val coroutine = rememberCoroutineScope()
    remember {
        navController.currentBackStackEntryFlow.onEach {
            println("Current: " + it.destination.route)
        }.launchIn(coroutine)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            HomeBottomNavigation(navController,currentRoute)
        },
        topBar = {
            Column {
                CustomTopBar()
                Spacer(modifier = Modifier.height(14.dp))
                if (currentRoute != JOB_DETAILS)
                {
                    OnDutyView(onDutyChanged = {})
                    Spacer(modifier = Modifier.height(23.dp))
                }
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

            composable(JOB_DETAILS) {
                val jobId = it.arguments?.getString("job_id")
                jobId?.let {
                    MapOrderDetailsScreen(jobID = jobId, navController)
                }
            }

            composable(BottomNavRouts.HELP) {

            }
            composable(BottomNavRouts.COMPLETE_JOB) {
                val jobId = it.arguments?.getString("job_id")
                jobId?.let {
                    CompleteJobForm(it, navController)
                }
            }

            composable(
                BottomNavRouts.WEB_VIEW
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
fun HomeBottomNavigation(navController: NavController, currentRoute: String?) {
    BottomNavigation(contentColor = Color.White, backgroundColor = Color.White) {
        BottomNavItem.values().forEach { screen ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null,
                        tint = if (currentRoute == screen.screenRoute) MaterialTheme.colorScheme.primary else UnSelectedItemColor
                    )
                },
                label = {
                    Text(
                        stringResource(screen.title),
                        style = CustomTypography.labelSmall.copy(
                            fontWeight = if (currentRoute == screen.screenRoute) FontWeight(700) else FontWeight(
                                500
                            ),
                            fontSize = 11.sp,
                        )
                    )
                },
                selected = currentRoute == screen.screenRoute,
                onClick = {
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
