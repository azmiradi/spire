package com.bumblebeeai.spire.app_entry_point.navigation.data.datasource

internal interface INavigationLocalDatasource {
    suspend fun isLoginUser(): Boolean
}