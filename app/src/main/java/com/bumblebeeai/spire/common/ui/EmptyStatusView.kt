package com.bumblebeeai.spire.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.theme.CustomTypography

@Preview
@Composable
fun EmptyStatusView() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = stringResource(id = R.string.no_tasks_here),
            style = CustomTypography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(64.dp))
        Icon(
            painter = painterResource(id = R.drawable.empty_icon),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .width(215.dp)
                .height(157.dp)
        )
        Spacer(modifier = Modifier.height(94.dp))

        Text(
            text = stringResource(id = R.string.yet),
            style = CustomTypography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}