package com.bumblebeeai.spire.common.domain.model.enums

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
enum class JobType(val filterType:String): Parcelable {
    New("all_tasks"), MY("my_tasks"), ALL("all_tasks,my_tasks");
}

fun JobType.getTypeAsParcel(): String {
    return Uri.encode(Gson().toJson(this))
}
