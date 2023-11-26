package com.bumblebeeai.spire.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumblebeeai.spire.R
import com.bumblebeeai.spire.home.BottomNavRouts.HELP
import com.bumblebeeai.spire.home.BottomNavRouts.MY_ACCOUNT
import com.bumblebeeai.spire.home.BottomNavRouts.MY_JOBS
import com.bumblebeeai.spire.home.BottomNavRouts.NEW_JOBS

object BottomNavRouts {
    const val NEW_JOBS = "new_jobs"
    const val MY_JOBS = "my_jobs"
    const val MY_ACCOUNT = "my_account"
    const val HELP = "help"
    const val WEB_VIEW = "web_view/{url}"

}

enum class BottomNavItem(
    @StringRes var title: Int,
    @DrawableRes var icon: Int,
    var screenRoute: String,
) {
    NewTasks(R.string.new_tasks, R.drawable.icon_task, NEW_JOBS),
    MyTasks(R.string.my_tasks, R.drawable.icon_task, MY_JOBS),
    MyAccount(R.string.my_account, R.drawable.icon_profile, MY_ACCOUNT),
    Help(R.string.help, R.drawable.icon_help, HELP);
}