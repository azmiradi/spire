package com.azmiradi.android_base.configuration

object AppConfiguration {
    lateinit var localStorageName: String
    lateinit var baseURL: String
    var connectTimeoutSec: Long = 1

    fun inti(localStorageName: String, baseURL: String, connectTimeoutSec: Long = 1) {
        this.localStorageName = localStorageName
        this.baseURL = baseURL
        this.connectTimeoutSec = connectTimeoutSec

    }
}