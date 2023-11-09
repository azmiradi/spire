package com.bumblebeeai.spire.auth.login.di

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.auth.login.data.datasources.local.ILoginLocalDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.local.LoginLocalDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.remote.ILoginRemoteDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.remote.LoginRemoteDataSource
import com.bumblebeeai.spire.auth.login.data.repository.LoginRepository
import com.bumblebeeai.spire.auth.login.domain.repository.ILoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LoginModule {
    @Provides
    fun provideLoginRepository(
        localDatasource: ILoginLocalDatasource,
        remoteDatasource: ILoginRemoteDatasource,
    ): ILoginRepository {
        return LoginRepository(localDatasource, remoteDatasource)
    }

    @Provides
    fun provideLoginLocalDatasource(
        storageKeyValue: IStorageKeyValue,
    ): ILoginLocalDatasource {
        return LoginLocalDatasource(storageKeyValue)
    }

    @Provides
    fun provideLoginRemoteDatasource(
        networkProvider: INetworkProvider,
    ): ILoginRemoteDatasource {
        return LoginRemoteDataSource(networkProvider)
    }
}