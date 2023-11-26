package com.bumblebeeai.spire.app_entry_point.navigation.framewoark.di

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.bumblebeeai.spire.app_entry_point.navigation.data.datasource.INavigationLocalDatasource
import com.bumblebeeai.spire.app_entry_point.navigation.data.datasource.NavigationLocalDatasource
import com.bumblebeeai.spire.app_entry_point.navigation.data.repository.NavigationRepository
import com.bumblebeeai.spire.app_entry_point.navigation.domain.repository.INavigationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {
    @Provides
    fun provideNavigationRepository(
        localDatasource: INavigationLocalDatasource,
    ): INavigationRepository {
        return NavigationRepository(localDatasource)
    }

    @Provides
    fun provideNavigationLocalDatasource(
        storageKeyValue: IStorageKeyValue,
    ): INavigationLocalDatasource {
        return NavigationLocalDatasource(storageKeyValue)
    }
}