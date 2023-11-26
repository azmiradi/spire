package com.bumblebeeai.spire.home.jobs.freamwork.di

import com.azmiradi.kotlin_base.domain.repository.local.keyValue.IStorageKeyValue
import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.auth.login.data.datasources.local.ILoginLocalDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.local.LoginLocalDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.remote.ILoginRemoteDatasource
import com.bumblebeeai.spire.auth.login.data.datasources.remote.LoginRemoteDataSource
import com.bumblebeeai.spire.auth.login.data.repository.LoginRepository
import com.bumblebeeai.spire.auth.login.domain.repository.ILoginRepository
import com.bumblebeeai.spire.home.jobs.data.datasources.local.IJobsLocalDataSource
import com.bumblebeeai.spire.home.jobs.data.datasources.local.JobsLocalDataSource
import com.bumblebeeai.spire.home.jobs.data.datasources.remote.IJobsRemoteDataSource
import com.bumblebeeai.spire.home.jobs.data.datasources.remote.JobsRemoteDataSource
import com.bumblebeeai.spire.home.jobs.data.repositories.JobsRepository
import com.bumblebeeai.spire.home.jobs.domain.repositories.IJobsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object JobsModule {
    @Provides
    fun provideJobsRepository(
        localDatasource: IJobsLocalDataSource,
        remoteDatasource: IJobsRemoteDataSource,
    ): IJobsRepository {
        return JobsRepository(localDatasource, remoteDatasource)
    }

    @Provides
    fun provideJobsLocalDatasource(
        storageKeyValue: IStorageKeyValue,
    ): IJobsLocalDataSource {
        return JobsLocalDataSource(storageKeyValue)
    }

    @Provides
    fun provideJobsRemoteDatasource(
        networkProvider: INetworkProvider,
    ): IJobsRemoteDataSource {
        return JobsRemoteDataSource(networkProvider)
    }
}