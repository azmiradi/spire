package com.bumblebeeai.spire.app_entry_point.navigation.data.repository

import com.bumblebeeai.spire.app_entry_point.navigation.data.datasource.INavigationLocalDatasource
import com.bumblebeeai.spire.app_entry_point.navigation.domain.repository.INavigationRepository
import javax.inject.Inject

internal class NavigationRepository @Inject constructor(private val navigationLocalDatasource: INavigationLocalDatasource) :
    INavigationRepository {
    override suspend fun isLoginUser(): Boolean {
        return navigationLocalDatasource.isLoginUser()
    }

}