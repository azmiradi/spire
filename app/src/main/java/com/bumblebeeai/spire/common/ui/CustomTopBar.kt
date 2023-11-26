package com.bumblebeeai.spire.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumblebeeai.spire.R

@Composable
fun CustomTopBar() {
    Box(Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 25.dp)
                .width(103.dp)
                .height(29.dp)
                .align(Alignment.Center)
        )
    }
}