package com.bumblebeeai.spire.common.ui.text_inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumblebeeai.spire.R

@Composable
fun BaseTextWithTitle(
    height: Dp = 50.dp,
    hint: String = "hi",
    label: String = "hi",
    isMandatory: Boolean = true,
    isError: Boolean = false,
    onChangeValue:(String)->Unit
) {
    var input by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        if (label.isNotEmpty()) {
            InputLabelTitle(isMandatory, label)
            Spacer(modifier = Modifier.height(4.dp))
        }


        BasicTextField(value = input,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (isError) Color.Red else Color(0xFFEBECED),
                    shape = RoundedCornerShape(size = 9.dp)
                )
                .padding(0.5.dp)
                .fillMaxWidth()
                .height(height)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 9.dp)),
            onValueChange = {
                input = it
                onChangeValue(it)
            },
            decorationBox = {
                if (hint.isNotEmpty())
                    Box(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = hint,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF4D4D4D),
                            )
                        )
                    }
            })
    }
}

@Composable
fun InputLabelTitle(
    isMandatory: Boolean,
    label: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF4D4D4D),
                letterSpacing = 0.17.sp,
            )
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = if (isMandatory) "(Required)" else "(Optional)",
            style = TextStyle(
                fontSize = 11.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(400),
                color = Color(0xFF979797),
                letterSpacing = 0.17.sp,
            )
        )
    }
}