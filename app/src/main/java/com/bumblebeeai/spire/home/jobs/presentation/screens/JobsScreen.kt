package com.bumblebeeai.spire.home.jobs.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumblebeeai.spire.common.domain.model.enums.JobType
import com.bumblebeeai.spire.common.ext.convertToBaseState
import com.bumblebeeai.spire.common.ui.AppCompose
import com.bumblebeeai.spire.home.BottomNavRouts.WEB_VIEW
import com.bumblebeeai.spire.home.jobs.presentation.component.JobItem
import com.bumblebeeai.spire.home.jobs.presentation.manager.JobEvent
import com.bumblebeeai.spire.home.jobs.presentation.manager.JobsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun JobsScreen(jobType: JobType, navController: NavHostController) {
    val viewModel = hiltViewModel<JobsViewModel>()
    val state = viewModel.viewState

    val jobs = state.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(JobEvent.GetJobs(jobType))
    }

    AppCompose(baseState = jobs.convertToBaseState(),
        onVachelCheckIsRequired = {
            val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
            navController.navigate(WEB_VIEW.replace("{url}", encodedUrl))
        })
    {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 19.dp)
        ) {
            items(jobs.itemCount) {
                jobs[it]?.let { it1 ->
                    JobItem(driverJob = it1) {

                    }
                }
            }
        }
    }
}
