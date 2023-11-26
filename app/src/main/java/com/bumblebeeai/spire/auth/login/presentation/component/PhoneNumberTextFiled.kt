package com.bumblebeeai.spire.auth.login.presentation.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumblebeeai.spire.auth.login.presentation.manager.LoginEvent
import com.simon.xmaterialccp.component.MaterialCountryCodePicker
import com.simon.xmaterialccp.data.ccpDefaultColors
import com.simon.xmaterialccp.data.utils.getDefaultLangCode
import com.simon.xmaterialccp.data.utils.getDefaultPhoneCode
import com.simon.xmaterialccp.data.utils.getLibCountries

@Composable
fun PhoneNumberTextFiled(error:Boolean = false,onValueChange:(String)->Unit) {
    val context = LocalContext.current
    var phoneCode by remember { mutableStateOf(getDefaultPhoneCode(context)) }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var defaultLang by rememberSaveable { mutableStateOf(getDefaultLangCode(context)) }

    MaterialCountryCodePicker(
        pickedCountry = {
            phoneCode = it.countryPhoneCode
            defaultLang = it.countryCode
        },
        defaultCountry = getLibCountries().single { it.countryCode == defaultLang },
        error = error,
        text = phoneNumber,
        onValueChange = {
            phoneNumber = it
            onValueChange("$phoneCode${phoneNumber}")
        },
        searchFieldPlaceHolderTextStyle = MaterialTheme.typography.bodyMedium,
        searchFieldTextStyle = MaterialTheme.typography.bodyMedium,
        phonenumbertextstyle = MaterialTheme.typography.bodyMedium,
        countrytextstyle = MaterialTheme.typography.bodyMedium,
        countrycodetextstyle = MaterialTheme.typography.bodyMedium,
        showCountryCodeInDIalog = true,
        showDropDownAfterFlag = true,
        textFieldShapeCornerRadiusInPercentage = 15,
        searchFieldShapeCornerRadiusInPercentage = 15,
        appbartitleStyle = MaterialTheme.typography.titleLarge,
        countryItemBgShape = RoundedCornerShape(5.dp),
        showCountryFlag = true,
        showCountryCode = true,
        isEnabled = true,
        phonehintnumbertextstyle = placeHolderStyle,
        colors = ccpDefaultColors(
            errorColor = MaterialTheme.colorScheme.error,
            outlineColor =  MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedOutlineColor = MaterialTheme.colorScheme.outline,
            surfaceColor = Color.White
            )
    )
}