package com.bumblebeeai.spire.app_entry_point

import android.app.Application
import com.azmiradi.android_base.configuration.AppConfiguration
import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppConfiguration.inti(
            localStorageName = "Spire",
            baseURL = BuildConfig.BASE_URL
        )

    }
}