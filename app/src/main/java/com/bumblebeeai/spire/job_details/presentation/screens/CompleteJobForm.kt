package com.bumblebeeai.spire.job_details.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.AppCompose
import com.bumblebeeai.spire.common.ui.text_inputs.BaseTextWithTitle
import com.bumblebeeai.spire.common.ui.text_inputs.InputLabelTitle
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.job_details.presentation.manager.JobDetailsViewModel

@Composable
fun CompleteJobForm(s: String, navController: NavHostController) {
    val viewModel = hiltViewModel<JobDetailsViewModel>()
    val state = viewModel.viewState.collectAsState().value

    AppCompose(baseState = state) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xE5FFFFFF))
                .padding(horizontal = 22.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            Spacer(modifier = Modifier.height(17.dp))
            BaseTextWithTitle(
                label = stringResource(id = R.string.odometer),
                hint = stringResource(id = R.string.ex15000),
            ) {

            }
            BaseTextWithTitle(
                label = stringResource(id = R.string.faults),
                hint = stringResource(id = R.string.add_faults),
            ) {

            }

            BaseTextWithTitle(
                label = stringResource(id = R.string.outcomes),
                hint = stringResource(id = R.string.add_outcomes),
            ) {

            }
            BaseTextWithTitle(
                height = 75.dp,
                isMandatory = false,
                label = stringResource(id = R.string.notes),
                hint = stringResource(id = R.string.add_note),
            ) {

            }

            UploadImages()
            Signature()

            Button(modifier = Modifier.fillMaxWidth(), onClick = {

            }) {
                Text(
                    text = stringResource(id = R.string.submit),
                    style = CustomTypography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700)
                    ),
                )
            }
        }
    }
}

@Composable
fun UploadImages() {
    InputLabelTitle(true, stringResource(id = R.string.upload_images))
    Box(
        Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFEBECED),
                shape = RoundedCornerShape(size = 9.dp)
            )
            .fillMaxWidth()
            .height(107.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 9.dp))
    ) {

    }

}


@Composable
fun Signature() {
    InputLabelTitle(false, stringResource(id = R.string.signature))
    Box(
        Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFEBECED),
                shape = RoundedCornerShape(size = 9.dp)
            )
            .fillMaxWidth()
            .height(107.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 9.dp))
    ) {

    }

}