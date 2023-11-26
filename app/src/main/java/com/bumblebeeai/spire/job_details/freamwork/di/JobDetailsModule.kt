package com.bumblebeeai.spire.job_details.freamwork.di

import com.azmiradi.kotlin_base.domain.repository.remote.INetworkProvider
import com.bumblebeeai.spire.job_details.data.datasources.remote.IJobDetailsRemoteDataSource
import com.bumblebeeai.spire.job_details.data.datasources.remote.JobDetailsRemoteDataSource
import com.bumblebeeai.spire.job_details.data.repositories.JobDetailsRepository
import com.bumblebeeai.spire.job_details.domain.repositories.IJobDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object JobDetailsModule {
    @Provides
    fun provideJobDetailsRepository(
        remoteDatasource: IJobDetailsRemoteDataSource,
    ): IJobDetailsRepository {
        return JobDetailsRepository(remoteDatasource)
    }

    @Provides
    fun provideJobDetailsRemoteDatasource(
        networkProvider: INetworkProvider,
    ): IJobDetailsRemoteDataSource {
        return JobDetailsRemoteDataSource(networkProvider)
    }
}