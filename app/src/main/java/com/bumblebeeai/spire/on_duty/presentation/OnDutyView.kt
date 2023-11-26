package com.bumblebeeai.spire.on_duty.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.common.ui.theme.CustomTypography
import com.bumblebeeai.spire.common.ui.theme.SelectedSwitchColor

@Composable
fun OnDutyView(onDutyChanged: (isEnabled: Boolean) -> Unit) {
    var switchCheckedState by rememberSaveable { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = stringResource(id = R.string.on_duty),
            style = CustomTypography.labelSmall.copy(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Switch(
            checked = switchCheckedState,
            onCheckedChange = { switchCheckedState = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = MaterialTheme.colorScheme.tertiary,
                checkedTrackColor = SelectedSwitchColor
            )
        )
        Spacer(modifier = Modifier.width(30.dp))
    }
}