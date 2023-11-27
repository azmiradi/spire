package com.bumblebeeai.spire.common.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.callNumber(number: String) {
    val intent = Intent(Intent.ACTION_CALL);
    intent.data = Uri.parse("tel:$number")
    startActivity(intent)
}