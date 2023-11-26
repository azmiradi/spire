package com.bumblebeeai.spire.app_entry_point.navigation.domain.repository

internal interface INavigationRepository {
    suspend fun isLoginUser(): Boolean
}