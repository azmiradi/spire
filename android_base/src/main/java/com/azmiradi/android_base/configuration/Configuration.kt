package com.azmiradi.android_base.configuration

object Configuration {
    internal var localStorageName: String = "Local Storage"

    fun inti(localStorageName: String = this.localStorageName) {
        this.localStorageName = localStorageName
    }
}